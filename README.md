# Mayday

## Thông tin cơ bản
API backend hiện đang được host tại địa chỉ [https://mayday.leafer.dev](https://mayday.leafer.dev)

Bản dựng trên production hiện tại gồm nhánh main và nhánh `requirements/auth`.

## Cách thức chạy local từ codebase:
- Clone repo
- Tại thư mục `main/java/resources/` thêm các file sau:
    - `database.properties` chứa thông tin kết nối đến database

- Chạy `./gradlew bootRun` hoặc `.\gradle.bat bootRun`, server sẽ chạy ở cổng 8080
