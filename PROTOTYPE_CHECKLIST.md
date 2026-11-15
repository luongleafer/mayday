# DrMayDay — Checklist liên kết UI và prototype

## Mục đích và cách dùng

Kiểm tra các màn hình Chi nhánh, Khoa và Phòng có đủ đường đi, đường quay lại và phản hồi phù hợp trước khi bàn giao. Đối chiếu `design.md`, kết quả kiểm kê Stitch đã cung cấp và lựa chọn **font Poppins** mới nhất của người dùng (ưu tiên hơn Inter trong `design.md`).

**Tình trạng hiện tại:** chưa trực tiếp kiểm tra các frame hay chạy prototype. Mọi ô dưới đây đều là **chưa kiểm tra**, không có nghĩa là đã đạt. Báo cáo của Stitch là thông tin tham khảo, không chứng minh liên kết hoạt động. Không tự tạo frame chỉ vì một dòng checklist chưa được đánh dấu: trước tiên tìm frame hoặc biến thể đã có.

Với mỗi lỗi, ghi: mã kiểm tra, tên/link frame nguồn và đích, thao tác, kết quả thực tế, kết quả mong đợi và ảnh hoặc ghi chú. Phân loại **Thiếu**, **Trùng**, **Sai liên kết**, **Sai nghiệp vụ**, **Cần xác nhận**. Sửa liên kết và tái sử dụng component trước khi đề xuất frame mới.

## 1. Kiểm kê trước khi nối

- [ ] Ghi danh sách frame thực tế, đường dẫn và tên rõ ràng theo module; phân biệt màn hình chính, drawer/dialog, biến thể và bản thử.
- [ ] Xác định mỗi module có danh sách, chi tiết, thêm, sửa và xác nhận xóa; xác nhận các trạng thái quan trọng trong từng mục dưới đây.
- [ ] Kiểm tra các màn hình do Stitch đánh dấu đã có bằng cách mở từng frame; kiểm tra các mục đánh dấu thiếu trước khi tạo.
- [ ] Đánh dấu các frame trùng: giữ một bản chuẩn và ghi các liên kết cần chuyển; chỉ xóa khi người sở hữu đồng ý.
- [ ] Một shell, font Poppins, màu sắc, nhãn, nút và component nhất quán; không có tính năng/trường ngoài phạm vi được duyệt.

## 2. Điều hướng chung

- [ ] NAV-01: Từ mỗi danh sách, chọn lần lượt Chi nhánh, Khoa, Phòng trên sidebar → đúng danh sách; mục đang chọn được đánh dấu.
- [ ] NAV-02: Chọn tên bản ghi → đúng chi tiết của chính bản ghi đó; breadcrumb và “Quay lại danh sách” → đúng module.
- [ ] NAV-03: Từ chi tiết Khoa, chọn Chi nhánh liên quan → đúng chi tiết chi nhánh. Từ chi tiết Phòng có khoa, chọn Khoa → đúng chi tiết khoa.
- [ ] NAV-04: Phòng chưa gán khoa hiển thị “Chưa được gán khoa”; không có liên kết khoa hoặc chi nhánh giả.
- [ ] NAV-05: Mỗi nút hiển thị có đích hoặc hành vi phù hợp; không có hotspot vô hình che nút khác, liên kết cụt, vòng lặp hoặc nút dẫn nhầm module.
- [ ] NAV-06: Drawer/dialog mở từ đúng màn hình, đóng bằng Hủy hoặc nút đóng → trở về đúng ngữ cảnh; không thay đổi dữ liệu trong kịch bản hủy.

## 3. Luồng Chi nhánh

- [ ] BR-01: Danh sách → “Thêm chi nhánh” → drawer tên và địa chỉ → nhập hợp lệ → tạo → danh sách mô phỏng có bản ghi và phản hồi thành công.
- [ ] BR-02: Để trống tên/địa chỉ → lỗi inline tương ứng, giữ form và dữ liệu khác; thử tên trùng → lỗi trùng tên.
- [ ] BR-03: Danh sách → chi tiết → chỉnh sửa → form điền sẵn → lưu → chi tiết hoặc danh sách mô phỏng cập nhật; hủy → dữ liệu cũ.
- [ ] BR-04: Chọn xóa → xác nhận đúng tên → Hủy quay lại; xác nhận xóa trường hợp hợp lệ → danh sách mô phỏng và phản hồi thành công.
- [ ] BR-05: Kịch bản có khoa trực thuộc hoặc nhân viên → thông báo không thể xóa, chỉ có Đóng; không có đường ép xóa.
- [ ] BR-06: Danh sách trống → “Thêm chi nhánh” mở đúng form.

## 4. Luồng Khoa

- [ ] DP-01: Danh sách → thêm khoa → nhập tên, chọn chi nhánh → tạo thành công theo dữ liệu mô phỏng; danh sách thể hiện đúng chi nhánh.
- [ ] DP-02: Thiếu tên/chi nhánh, chi nhánh không còn hợp lệ và tên trùng trong cùng chi nhánh → lỗi phù hợp, không mất thông tin đã nhập.
- [ ] DP-03: Khi không có chi nhánh để chọn → giải thích rõ và liên kết đến mục Chi nhánh; không gửi form thiếu chi nhánh.
- [ ] DP-04: Danh sách → chi tiết → sửa với dữ liệu điền sẵn → lưu/hủy → đúng ngữ cảnh và dữ liệu mô phỏng.
- [ ] DP-05: Xóa khoa → dialog nêu rõ phòng được giữ lại và không còn liên kết khoa; Hủy giữ nguyên. Xác nhận → danh sách khoa không còn bản ghi, phòng liên quan vẫn có trong danh sách phòng với nhãn chưa gán khoa.
- [ ] DP-06: Danh sách trống → “Thêm khoa” mở đúng form.
- [ ] DP-07: Form tạo khoa đang thiếu trường `type` bắt buộc theo DTO hiện tại: đánh dấu **cần xác nhận**, không tự coi prototype này chứng minh có thể tạo khoa qua backend.

## 5. Luồng Phòng

- [ ] RM-01: Danh sách → thêm phòng → nhập tên, chọn khoa hợp lệ → tạo thành công trong kịch bản mô phỏng.
- [ ] RM-02: Thiếu tên hoặc khoa → lỗi inline tương ứng; khoa không còn hợp lệ → yêu cầu chọn lại, giữ dữ liệu đã nhập.
- [ ] RM-03: Combobox hiển thị “Tên khoa — Tên chi nhánh”; khi không có khoa → giải thích và liên kết đến mục Khoa.
- [ ] RM-04: Mở chi tiết phòng có khoa và phòng chưa gán khoa → thông tin tương ứng, quay lại đúng danh sách.
- [ ] RM-05: Sửa phòng đã có khoa → dữ liệu điền sẵn → lưu/hủy đúng ngữ cảnh. Phòng chưa gán khoa có thể minh họa chọn khoa mới; **chưa kết luận bắt buộc chọn lại khoa khi chỉ sửa tên**, chờ xác nhận.
- [ ] RM-06: Xóa phòng hợp lệ → xác nhận đúng tên → thành công; phòng còn nhân viên → thông báo bị chặn, chỉ có Đóng, không có đường ép xóa.
- [ ] RM-07: Danh sách trống → “Thêm phòng” mở đúng form.

## 6. Trạng thái chung và kiểm tra thao tác

- [ ] STATE-01: Có thể xem loading, empty và lỗi tải riêng biệt; lỗi tải có “Thử lại” và không giả làm danh sách trống.
- [ ] STATE-02: Lỗi lưu giữ nội dung form; đang gửi thể hiện tiến trình, không báo thành công sớm hoặc cho gửi lặp trong kịch bản mô phỏng.
- [ ] STATE-03: Bản ghi không tồn tại → thông báo và đường về đúng danh sách; người không có quyền → trang từ chối không lộ dữ liệu hay thao tác quản trị.
- [ ] STATE-04: Toast/banner và các biến thể form được dùng lại; không cần nhân bản cả trang cho mọi lỗi nhỏ.
- [ ] UX-01: Tab, Shift+Tab, Enter, Space và Escape hoạt động theo khả năng của công cụ prototype; focus thấy rõ, overlay không che nút đóng và trả focus hợp lý nếu công cụ hỗ trợ.
- [ ] UX-02: Nhãn, nội dung dài, tên và địa chỉ không bị cắt; nút phá hủy có nhãn rõ; lỗi có chữ chứ không chỉ màu.
- [ ] UX-03: Kiểm tra desktop và mobile 390 px: điều hướng, form, danh sách và dialog sử dụng được, không tràn ngang toàn trang.
- [ ] UX-04: Chuyển trang/overlay nhất quán, không giật hoặc chồng sai lớp; người dùng không bị kẹt, không có bước thừa. Ghi nhận thực tế trên thiết bị/công cụ đã kiểm tra.

## 7. Kịch bản demo xuyên module

1. [ ] Từ danh sách Chi nhánh, mở chi tiết → sửa → lưu và quay lại danh sách.
2. [ ] Mở Khoa thuộc chi nhánh đó → xem chi tiết → mở dialog xóa → đọc cảnh báo giữ phòng → xác nhận.
3. [ ] Chuyển sang Phòng → tìm phòng của khoa vừa xóa → thấy phòng còn tồn tại, chưa gán khoa → mở chi tiết.
4. [ ] Mở sửa phòng → minh họa chọn khoa hợp lệ khác → lưu → thấy khoa mới trong dữ liệu mô phỏng.
5. [ ] Mở xóa phòng có nhân viên → thấy thông báo bị chặn → Đóng → tiếp tục điều hướng bình thường.

Dùng bộ dữ liệu giả thống nhất giữa các frame. Nếu công cụ không hỗ trợ dữ liệu động, nối các frame trạng thái trước/sau bằng hotspot và ghi rõ đây là mô phỏng; không tuyên bố dữ liệu thật đã được cập nhật hay backend đã được kiểm thử.

## 8. Báo cáo kết quả

| Mã | Frame nguồn → đích | Kết quả: Đạt / Lỗi / Chưa kiểm tra / Cần xác nhận | Bằng chứng và cách xử lý |
| --- | --- | --- | --- |
| Ví dụ NAV-01 | Chi nhánh / Danh sách → Khoa / Danh sách | Chưa kiểm tra | Chưa chạy prototype |

Kết luận chỉ khi đã kiểm tra thực tế: tổng số đạt/lỗi/chưa kiểm tra, luồng bị chặn, frame thừa hoặc trùng, hành động cần bổ sung, các điểm chờ xác nhận và thiết bị/công cụ đã dùng. Prototype minh họa tương tác; việc kiểm tra prototype không chứng minh API, dữ liệu hay ứng dụng thật hoạt động.

## 9. Prompt kiểm tra cho Stitch hoặc người review

> Đối chiếu các frame hiện có với `PROTOTYPE_CHECKLIST.md` và `design.md`, ưu tiên font Poppins. Kiểm kê frame và liên kết hiện có, sau đó chạy hoặc kiểm tra từng luồng nếu công cụ cho phép. Báo cáo Đạt/Lỗi/Chưa kiểm tra/Cần xác nhận kèm tên frame nguồn, đích và bằng chứng. Phân biệt điều đã quan sát với điều chỉ suy luận. Chỉ liệt kê màn hình thiếu hoặc trùng và đề xuất tái sử dụng component; chưa tạo, xóa hoặc thay đổi frame. Không tự chốt Loại khoa hay quy tắc sửa phòng chưa gán khoa.
