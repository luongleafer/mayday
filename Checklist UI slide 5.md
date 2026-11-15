# Checklist UI Slide 5 (Quản lý bệnh nhân & hồ sơ bệnh án)

## 1. Điều hướng và phân quyền

- [ ] NAV-01 — Từ navbar của bác sĩ, mở **Bệnh nhân** và **Bệnh án** → đúng danh sách, mục đang mở được đánh dấu. Lễ tân có điểm vào thêm bệnh nhân và danh sách điều phối theo phạm vi đã xác nhận.
- [ ] NAV-02 — Từ kết quả tìm bệnh nhân, chọn **Tạo bệnh án** hoặc **Xem lịch sử** → đúng người. Từ danh sách hoặc thẻ lịch sử, chọn **Xem chi tiết** → đúng mã bệnh án.
- [ ] NAV-03 — Từ chi tiết hoặc lịch sử, breadcrumb và nút quay lại trả về đúng trang nguồn. Kiểm tra bộ lọc hoặc từ khóa được giữ khi quay về, nếu prototype hỗ trợ.
- [ ] NAV-04 — Bệnh nhân chỉ có đường vào **Bệnh án của tôi** và lịch sử của mình. Thử mở nhánh dữ liệu người khác → màn hình từ chối quyền, không lộ tên, phiếu hay nội dung hồ sơ.
- [ ] NAV-05 — Bác sĩ thấy **Tiếp nhận** ở hồ sơ đang chờ. Bác sĩ phụ trách thấy **Chốt bệnh án** ở hồ sơ đang khám. Quản trị viên có biến thể **Xóa bệnh án**. Kiểm tra nút theo cả vai trò lẫn trạng thái.
- [ ] NAV-06 — Drawer/dialog mở từ đúng màn hình. **Hủy** hoặc nút đóng → đúng ngữ cảnh và giữ nguyên dữ liệu. Kiểm tra nút cụt, hotspot chồng nhau hoặc đích sai vai trò.
- [ ] NAV-07 — **Cần xác nhận** quyền tìm kiếm, mở chi tiết, xem lịch sử hoặc tạo bệnh án của lễ tân, cùng điểm vào và quyền xem hồ sơ của quản trị viên. Dùng biến thể review theo brief trong khi chờ xác nhận.

## 2. Tìm và tạo bệnh nhân

- [ ] PAT-01 · `docpat01` — Tìm bằng tên và bằng số điện thoại → đúng hồ sơ mẫu. Kiểm tra trang trước/trang sau và tìm từ khóa mới → quay về trang đầu.
- [ ] PAT-02 · `docpat01` — Không có kết quả → hiện “Không tìm thấy bệnh nhân phù hợp”, có đường sửa từ khóa hoặc **Thêm bệnh nhân** theo quyền. Thử vai thiếu quyền → phản hồi `3103` và không hiển thị kết quả.
- [ ] PAT-03 · `docpat02` — **Thêm bệnh nhân** → drawer → nhập dữ liệu hợp lệ → phản hồi thành công → **Tạo bệnh án** cho đúng bệnh nhân vừa tạo.
- [ ] PAT-04 · `docpat02` — Nhập số đã có → lỗi `3006` dưới ô số điện thoại, giữ thông tin đã nhập và có đường quay lại tra cứu bản ghi hiện có. Hủy drawer → dữ liệu mẫu giữ nguyên.
- [ ] PAT-05 · `docpat02` — **Cần xác nhận** toàn bộ trường và tính bắt buộc theo DTO. “Họ tên” và “Số điện thoại” là trường minh họa của prototype.

## 3. Phân loại và tạo bệnh án

- [ ] CRT-01 · `medrec01` — Bác sĩ bắt đầu từ bệnh nhân đã chọn, bệnh nhân bắt đầu từ chính tài khoản của mình → màn hình phân loại hiển thị đúng người và các câu hỏi mẫu.
- [ ] CRT-02 · `medrec02` — Trả lời đầy đủ rồi bấm **Tiếp tục** → đúng màn hình xác nhận mẫu. **Quay lại** → giữ câu trả lời để chỉnh.
- [ ] CRT-03 · `medrec02` — Bỏ sót câu phân loại → lỗi `4006` tại bước phân loại, chỉ rõ câu thiếu và giữ các lựa chọn đã nhập.
- [ ] CRT-04 · `medrec03` — Xác nhận mẫu hợp lệ → tạo thành công → đúng chi tiết bệnh án mới, mã hồ sơ nhất quán, trạng thái **Chờ tiếp nhận** và chưa có bác sĩ phụ trách.
- [ ] CRT-05 · `medrec03` — Mẫu hết khả dụng → lỗi `4101` tại bước tạo hồ sơ, dẫn về phân loại để chọn lại và giữ lựa chọn đã nhập nếu prototype hỗ trợ.

## 4. Danh sách, tóm tắt, chi tiết và lịch sử

- [ ] REC-01 · `medrec04` — Danh sách toàn viện → thử **Tất cả**, **Chờ tiếp nhận**, **Đang khám**, **Đã chốt** → đúng tập hồ sơ theo bộ lọc `status`. Kiểm tra trạng thái không có kết quả lọc.
- [ ] REC-02 · `medrec05` — Bấm từ danh sách → đúng mã hồ sơ, đúng bệnh nhân, người tạo và bác sĩ phụ trách. Kiểm tra đủ ba trạng thái và đường quay lại.
- [ ] REC-03 · `medrec05` — Mở mã hồ sơ không tồn tại hoặc đã xóa → lỗi `4001`, có đường về đúng danh sách theo vai trò.
- [ ] REC-04 · `medrec06` — Thẻ tóm tắt hiển thị đúng bệnh nhân, mã hồ sơ theo thiết kế, ngày tạo và trạng thái. Bấm thẻ ở lịch sử → đúng chi tiết. Kiểm tra dữ liệu tóm tắt khớp chi tiết sau khi tiếp nhận hoặc chốt.
- [ ] REC-05 · `medrec13` — Bác sĩ mở lịch sử một bệnh nhân, bệnh nhân mở lịch sử của mình → đúng người và mới nhất trước. Kiểm tra thêm lịch sử chưa có bệnh án.
- [ ] REC-06 · `medrec13` — Bệnh nhân A thử mở lịch sử bệnh nhân B → lỗi `3106), không lộ dữ liệu và có đường về **Bệnh án của tôi**.

## 5. Phiếu trả lời, tiếp nhận, chốt và xóa

### Phiếu trả lời

- [ ] ANS-01 · `medrec08` — Mở **Phiếu trả lời** → đúng câu hỏi theo thứ tự của mẫu và đúng câu trả lời đã lưu. Kiểm tra hồ sơ chưa có câu trả lời.
- [ ] ANS-02 · `medrec10` — Bệnh nhân điền phiếu của mình khi đang chờ, bác sĩ phụ trách sửa khi đang khám → lưu thành công. Mở lại → một câu trả lời hiện tại cho mỗi câu hỏi.
- [ ] ANS-03 · `medrec10` — Hồ sơ đã chốt hiển thị phiếu chỉ đọc. Nếu hồ sơ vừa được chốt trong lúc sửa → lỗi `4003` và nút **Tải lại hồ sơ**.

### Tiếp nhận và chốt

- [ ] ACC-01 · `medrec07` — Bác sĩ nhận hồ sơ đang chờ → **Đang khám** và đúng bác sĩ phụ trách. Đối chiếu trạng thái ở danh sách, thẻ tóm tắt và trang chi tiết.
- [ ] ACC-02 · `medrec07` — Hồ sơ đã được bác sĩ khác nhận → `4005`. Trường hợp khác mà hồ sơ đã rời trạng thái chờ → `4004`. Kiểm tra thông báo riêng và đường **Tải lại hồ sơ**.
- [ ] CLS-01 · `medrec11` — Bác sĩ phụ trách nhập chỉ dẫn → mở xác nhận chốt → **Hủy** quay về form với dữ liệu đang nhập. Xác nhận chốt → hiện **Đã chốt**, thời điểm chốt, chỉ dẫn đã lưu và phiếu chỉ đọc.
- [ ] CLS-02 · `medrec11` — Thử chốt bằng bác sĩ khác trong kịch bản lỗi → `3105`, hồ sơ giữ nguyên trạng thái và chỉ dẫn đang nhập được giữ nếu đã có form.

### Xóa bệnh án

- [ ] DEL-01 · `medrec14` — Quản trị viên mở hộp thoại → đúng mã bệnh án và tên bệnh nhân. Cảnh báo nêu rõ câu trả lời bị xóa cùng hồ sơ, tệp đính kèm được giữ và gỡ liên kết. **Hủy** → hồ sơ còn nguyên.
- [ ] DEL-02 · `medrec14` — Xác nhận xóa → phản hồi thành công và quay về màn hình nguồn. Danh sách mô phỏng bỏ hồ sơ đã xóa. Nếu chạy nhánh mở lại từ liên kết cũ → lỗi `4001`.
- [ ] DEL-03 · `medrec14` — Bác sĩ và lễ tân không thấy nút xóa. Biến thể yêu cầu bị từ chối → lỗi `3102`, hồ sơ vẫn còn.

### Kịch bản demo

1. [ ] DEMO-01 — **Bác sĩ Minh:** tìm bệnh nhân → thử nhánh không có kết quả → thêm bệnh nhân hoặc chọn hồ sơ có sẵn → phân loại → tạo bệnh án **Chờ tiếp nhận**.
2. [ ] DEMO-02 — **Bệnh nhân:** mở bệnh án của mình → lưu phiếu khi đang chờ → mở lịch sử → bấm thẻ tóm tắt → đúng mã chi tiết. Thử nhánh bị từ chối khi truy cập lịch sử người khác.
3. [ ] DEMO-03 — **Bác sĩ Minh:** tiếp nhận → sửa phiếu → chốt. **Bác sĩ An:** đi qua biến thể xung đột tiếp nhận hoặc sai người phụ trách. Mở lại hồ sơ đã chốt → phiếu chỉ đọc.
4. [ ] DEMO-04 — **Quản trị viên:** mở hồ sơ mẫu → xem hệ quả xóa → thử hủy → mở lại và xác nhận → thấy phản hồi thành công và danh sách mô phỏng cập nhật.

