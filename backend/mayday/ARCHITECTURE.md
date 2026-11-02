# Clinic topology module

## Responsibilities

Requests flow through `controllers -> services -> repositories -> entities`.

- `controllers`: HTTP routes, request validation and response envelopes. Controllers do not handle SQL exceptions or implement business rules.
- `services`: use cases, parent lookup, duplicate checks and transaction boundaries. Delete operations return `void`; HTTP response bodies belong to controllers.
- `entities`: persistent state and explicit `updateDetails` operations. Database identities and relationships keep their existing mappings.
- `mappers/TopologyMapper`: converts entities into response snapshots within the service transaction, before lazy relationships become detached.
- `dto/requests` and `dto/responses`: API boundary objects. Response records are immutable; the branch JSON field remains `is_active` for compatibility.
- `exceptions`: translates application and persistence errors into the shared response format.
- `utils`: text normalization and database error classification.

Service classes use constructor injection. Separate service interfaces and generic CRUD base classes are intentionally omitted: there is only one implementation of each use case, and branch/department/room rules differ. Introduce an interface when a genuine alternative implementation or module boundary requires it.

## API compatibility

The 15 existing routes and `set/edit` method names are retained. Branch creation keeps HTTP 200 with code `1001`; department/room creation keeps HTTP 201. PUT requests update only non-null fields. An empty optional string clears its value; a missing or null field keeps it unchanged. Required strings cannot be blank.

Branch errors now use the same centralized contract as the other resources: HTTP 404 / `2001` for missing resources, HTTP 400 / `2003` for invalid input, HTTP 409 / `2005` for deletion blocked by a foreign key. This replaces the branch controller's previous local HTTP 400 handling.

## Persistence and tests

Production uses schema validation, never schema generation. Existing mappings are `chi_nhanh`, `khoaphong` and `phongkham`; confirm these against the deployed schema. No migration is included. Database foreign keys remain the final authority for deletion restrictions and concurrent writes.

Tests explicitly use isolated H2 databases and disable imported database credentials. They verify the HTTP workflow, validation and foreign key restrictions. They do not establish PostgreSQL server compatibility or the presence of future employee foreign keys.
