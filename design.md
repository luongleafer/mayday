# DrMayDay — Design brief cho Google Stitch

## 1. Yêu cầu tạo giao diện

Thiết kế bộ giao diện web quản trị DrMayDay cho phần **Quản lý Cơ sở vật chất & Hạ tầng tổ chức**. Người dùng là Admin của hệ thống quản lý khám bệnh và theo dõi mề đay. Toàn bộ chữ trên giao diện dùng tiếng Việt có dấu.

Tạo các màn hình nhất quán, dùng chung sidebar, header, bảng dữ liệu, biểu mẫu và hộp thoại. Ưu tiên thao tác quản trị rõ ràng, dễ đọc, ít bước. Phạm vi chỉ gồm **Chi nhánh, Khoa, Phòng**. Đầu ra mong muốn là thiết kế UI và các trạng thái để review, chưa phải ứng dụng kết nối API.

## 2. Căn cứ và phạm vi chắc chắn

- Đã đọc nội dung văn bản trong `SLIDE4_DrMayDay.pptx`, đặc biệt mô tả và test case ở trang 10–12, 19–21, 28–30.
- Dùng `CODEX_FIGMA_DESIGN_WORKFLOW.md` làm tài liệu tham chiếu về phạm vi và phong cách. Yêu cầu hiện tại chỉ là tạo brief Markdown cho Stitch, không thực hiện toàn bộ quy trình Figma trong tài liệu đó.
- Chưa tìm thấy `docs/frontend/design-system.md`. Dùng palette và Inter trong tài liệu workflow làm cơ sở.
- Đã đối chiếu DTO tạo/sửa Branch, Department, Room trong backend. Khác biệt với Slide 4 được ghi ở mục 11, không âm thầm bổ sung vào bản thiết kế cơ sở.
- Kích thước, khoảng cách, responsive và cách trình bày dưới đây là đề xuất thiết kế, không phải quy tắc nghiệp vụ từ Slide 4.

## 3. Cấu trúc thông tin và điều hướng

Quan hệ: **Chi nhánh → Khoa → Phòng**. Khoa bắt buộc thuộc một chi nhánh. Phòng mới phải thuộc một khoa hợp lệ. Phòng vẫn tồn tại và có thể không còn khoa sau khi khoa bị xóa.

Sidebar hiển thị wordmark chữ “DrMayDay”, tên nhóm “Cơ sở vật chất” và đúng ba mục:

1. Chi nhánh
2. Khoa
3. Phòng

Header có tên khu vực quản trị và nhãn vai trò “Quản trị viên”. Không cần thêm tìm kiếm toàn cục, thông báo hoặc menu tính năng chưa được mô tả. Mỗi trang có breadcrumb, tiêu đề, mô tả ngắn và một nút tạo mới chính.

Tên bản ghi trong bảng mở trang chi tiết. Mỗi hàng có thao tác “Chỉnh sửa” và “Xóa”, có nhãn hoặc tooltip dễ hiểu. Trang chi tiết có “Quay lại danh sách”, “Chỉnh sửa”, “Xóa”. Liên kết khoa → chi nhánh và phòng → khoa giúp hiểu quan hệ; không tạo trang quản lý nhân sự.

## 4. Hệ thống thị giác

Phong cách quản trị y tế sáng, gọn, chuyên nghiệp. Bảng dữ liệu là trọng tâm, không dùng hero marketing hay ảnh bác sĩ trang trí.

| Vai trò | Màu | Cách dùng |
| --- | --- | --- |
| Primary | `#2C5EAD` | Nút chính, mục điều hướng đang chọn |
| Secondary | `#1591DC` | Điểm nhấn phụ; kiểm tra tương phản trước khi dùng cho chữ nhỏ |
| Accent | `#4BB8FA` | Chi tiết nhấn tương tác |
| Light Blue | `#C4E2F5` | Nền vùng được chọn |
| Cream | `#FFF9D2` | Nền thông tin/cảnh báo nhẹ |
| Surface | `#FFFFFF` | Bảng, biểu mẫu, hộp thoại |
| Background | `#F5F8FC` | Nền nội dung |
| Border | `#E5EAF1` | Viền và đường phân cách |
| Text | `#253449` | Chữ chính |
| Muted | `#64748B` | Chữ phụ |

Đề xuất thêm màu ngữ nghĩa lỗi/nguy hiểm `#B42318`, nền lỗi `#FEF3F2`; đây là token bổ sung cho trạng thái, không thay màu thương hiệu. Lỗi luôn có thông điệp chữ, không chỉ có màu.

- Font Inter với fallback sans-serif hỗ trợ tiếng Việt.
- Tiêu đề trang 28/36 px, semibold; tiêu đề khối 18/28 px, semibold; nội dung 14/22 px; input 16/24 px; chú thích 12/18 px.
- Thang spacing: 4, 8, 12, 16, 24, 32, 48 px. Khoảng cách giữa các khối chính 24 px.
- Input/nút cao 44 px; hàng bảng tối thiểu 56 px; địa chỉ dài được xuống dòng.
- Bo góc control 8 px, panel 12 px. Viền mảnh, bóng nhẹ chỉ khi cần phân lớp overlay.
- Icon nét cùng một bộ, 20 px. Icon trang trí không thay nhãn.
- Nút primary nền xanh đậm/chữ trắng; secondary nền trắng/viền; destructive màu đỏ ngữ nghĩa và có xác nhận.
- Hiển thị default, hover, focus, disabled, loading cho control dùng lại. Focus bàn phím rõ; vùng bấm tối thiểu 44 × 44 px; tương phản chữ thông thường hướng tới 4.5:1.

## 5. Bố cục và responsive

- Desktop mẫu 1440 × 1000 px: sidebar 240 px, header 64 px, nội dung padding 32 px. Không kéo giãn khoảng trống chỉ để lấp màn hình.
- Mỗi trang danh sách: breadcrumb → tiêu đề và nút tạo → mô tả → bảng trên nền trắng.
- Form tạo/sửa dùng drawer phải rộng khoảng 480 px, footer chứa “Hủy” và “Tạo …” hoặc “Lưu thay đổi”. Drawer cuộn nội dung khi dài, hành động vẫn dễ tiếp cận.
- Chi tiết dùng trang riêng với thông tin theo cặp nhãn/giá trị, đồng nhất thứ tự trường với form.
- Hộp thoại xác nhận rộng khoảng 440 px, nêu rõ tên bản ghi và hệ quả. Nút “Hủy” và nút xóa có nhãn cụ thể.
- Tablet 768 px: sidebar thu gọn, padding 24 px; bảng được cuộn ngang trong vùng riêng nếu cần.
- Mobile 390 px: điều hướng mở qua nút menu; padding 16 px; bảng chuyển thành danh sách thẻ giữ đủ trường và thao tác; drawer thành form toàn màn hình. Không cuộn ngang toàn trang.
- Overlay giữ focus bên trong, có nút đóng, trả focus về điểm mở. Thông tin quan trọng không chỉ nằm trong tooltip.

## 6. Chi nhánh

### Danh sách và chi tiết

Tiêu đề “Chi nhánh”; mô tả “Quản lý các cơ sở y tế trong hệ thống”; CTA “Thêm chi nhánh”. Bảng gồm **Tên chi nhánh | Địa chỉ | Thao tác**. Chi tiết chỉ hiển thị các trường này và hành động chung.

### Form

- “Tên chi nhánh *”: text input.
- “Địa chỉ *”: ô nhập nhiều dòng.
- Bản sửa điền sẵn dữ liệu; giữ tên và địa chỉ đầy đủ.
- Tên chi nhánh không được trùng trong toàn hệ thống.

### Trạng thái cần vẽ

- Thiếu tên: “Vui lòng nhập tên chi nhánh.”
- Thiếu địa chỉ: “Vui lòng nhập địa chỉ.”
- Trùng tên, dưới input tên: “Tên chi nhánh đã tồn tại. Vui lòng chọn tên khác.”
- Xác nhận: “Xóa chi nhánh «{tên}»?”; nút “Xóa chi nhánh”.
- Bị chặn: “Không thể xóa chi nhánh này vì vẫn còn khoa trực thuộc hoặc nhân viên được phân công. Vui lòng xử lý các liên kết này trước khi xóa.” Chỉ có “Đóng”, không có nút ép xóa.
- Thành công: “Đã tạo chi nhánh.” / “Đã lưu thay đổi.” / “Đã xóa chi nhánh.”

## 7. Khoa

### Danh sách và chi tiết

Tiêu đề “Khoa”; mô tả “Quản lý các khoa theo chi nhánh”; CTA “Thêm khoa”. Bảng gồm **Tên khoa | Chi nhánh | Thao tác**. Chi tiết hiển thị tên khoa và chi nhánh liên quan.

### Form

- “Tên khoa *”: text input; tính bắt buộc được xác nhận thêm trong DTO hiện tại.
- “Chi nhánh *”: combobox chọn bản ghi hợp lệ, hiển thị tên thay vì ID kỹ thuật.
- Tên khoa phải duy nhất trong cùng chi nhánh; không áp đặt duy nhất toàn hệ thống.
- Khi danh sách chi nhánh trống: “Chưa có chi nhánh để chọn.” và liên kết tới mục Chi nhánh. Không cho lưu khi chưa chọn được chi nhánh.

### Trạng thái cần vẽ

- Thiếu tên: “Vui lòng nhập tên khoa.”
- Chưa chọn chi nhánh: “Vui lòng chọn chi nhánh.”
- Chi nhánh không còn hợp lệ: “Chi nhánh đã chọn không còn khả dụng. Vui lòng chọn lại.” Giữ dữ liệu đang nhập.
- Trùng tên: “Tên khoa đã tồn tại trong chi nhánh này.”
- Xác nhận xóa: “Xóa khoa «{tên}»?” và **“Các phòng trực thuộc vẫn được giữ lại nhưng sẽ không còn liên kết với khoa này.”** Nút “Hủy” / “Xóa khoa”.
- Xóa thành công: “Đã xóa khoa. Các phòng trực thuộc được giữ lại và chưa được gán khoa.”

Không chặn xóa khoa chỉ vì khoa đang có phòng. Không mô tả thao tác này là xóa cả phòng.

## 8. Phòng

### Danh sách và chi tiết

Tiêu đề “Phòng”; mô tả “Quản lý phòng khám và phòng chức năng”; CTA “Thêm phòng”. Bảng gồm **Tên phòng | Khoa | Thao tác**. Có ít nhất một dòng **“Chưa được gán khoa”** để minh họa phòng còn lại sau khi xóa khoa. Đây là trạng thái quan hệ, không phải trường trạng thái hoạt động.

Chi tiết phòng hiển thị tên và khoa. Nếu không có khoa, hiện “Chưa được gán khoa” và giải thích “Phòng vẫn tồn tại trong hệ thống.” Không suy ra chi nhánh cho phòng đã mất liên kết khoa.

### Form

- “Tên phòng *”: text input.
- “Khoa *”: combobox bắt buộc khi tạo; các lựa chọn hiển thị “Tên khoa — Tên chi nhánh” để phân biệt khoa trùng tên.
- Không tạo thêm trường chi nhánh độc lập cho phòng.
- Khi không có khoa: “Chưa có khoa để chọn.” và liên kết tới mục Khoa.
- Bản sửa phòng đã có khoa hiển thị lựa chọn hiện tại. Quy tắc sửa phòng đang không có khoa cần xác nhận riêng theo mục 11.

### Trạng thái cần vẽ

- Thiếu tên: “Vui lòng nhập tên phòng.”
- Chưa chọn khoa khi tạo: “Vui lòng chọn khoa.”
- Khoa không còn hợp lệ: “Khoa đã chọn không còn khả dụng. Vui lòng chọn lại.”
- Xác nhận xóa: “Xóa phòng «{tên}»?”; nút “Hủy” / “Xóa phòng”.
- Bị chặn: “Không thể xóa phòng này vì vẫn còn bác sĩ hoặc nhân viên y tế được phân công. Vui lòng xử lý việc phân công trước khi xóa.” Nút “Đóng”.
- Không thêm quy tắc trùng tên phòng khi nguồn chưa xác nhận.

## 9. Trạng thái chung và hành vi

Áp dụng cho cả ba module:

- **Loading:** skeleton theo cấu trúc bảng; combobox có trạng thái đang tải.
- **Empty:** “Chưa có chi nhánh/khoa/phòng”, mô tả ngắn và CTA tạo tương ứng.
- **Lỗi tải:** “Không tải được dữ liệu. Vui lòng thử lại.” và nút “Thử lại”. Không dùng empty state cho lỗi tải.
- **Đang lưu/xóa:** spinner trong nút; vô hiệu hóa thao tác gửi lặp; không hiện thành công trước kết quả.
- **Lỗi lưu:** giữ dữ liệu người dùng nhập, hiện lỗi tại trường nếu xác định được, hoặc banner “Không thể lưu thay đổi. Vui lòng thử lại.”
- **Bản ghi không tồn tại:** “Không tìm thấy chi nhánh/khoa/phòng này. Dữ liệu có thể đã được xóa.” và “Về danh sách”. Áp dụng khi đọc, sửa hoặc xóa bản ghi đã biến mất.
- **Không có quyền:** “Bạn không có quyền truy cập khu vực này. Chỉ quản trị viên được phép quản lý cơ sở vật chất.” Không hiển thị dữ liệu quản trị hoặc nút thao tác.
- **Thành công:** toast ngắn; đóng form và cập nhật danh sách về mặt mô tả tương tác.
- **Validation:** ký hiệu * được giải thích là bắt buộc; nhãn luôn hiện; lỗi nằm dưới input, focus đến trường lỗi đầu tiên.

Không hiển thị mã lỗi API, tên bảng, khóa ngoại, `NULL`, exception hoặc chuỗi lỗi kỹ thuật như “User existed” trên giao diện. Phân biệt thiếu quyền với xóa bị chặn do liên kết dữ liệu, dù backend có thể dùng cùng mã lỗi.

## 10. Danh sách đầu ra Stitch cần tạo

| Nhóm | Màn hình / biến thể bắt buộc |
| --- | --- |
| Shared | Shell desktop; mẫu mobile; bộ nút, input, combobox, table, drawer, dialog, toast |
| Chi nhánh | Danh sách có dữ liệu; chi tiết; tạo; sửa; thiếu trường; trùng tên; xác nhận xóa; xóa bị chặn |
| Khoa | Danh sách có dữ liệu; chi tiết; tạo; sửa; thiếu/sai chi nhánh; trùng tên trong chi nhánh; xác nhận xóa có cảnh báo giữ phòng |
| Phòng | Danh sách có cả phòng chưa gán khoa; chi tiết có khoa và không có khoa; tạo; sửa có khoa; thiếu tên/khoa; xác nhận xóa; xóa bị chặn |
| Trạng thái chung | Loading; empty cho từng module; lỗi tải; lỗi lưu; đang gửi; thành công; không tìm thấy; không có quyền |

Đặt tên màn hình theo module và trạng thái, ví dụ `Branches / List`, `Departments / Delete confirmation`, `Rooms / Unassigned detail`. Dùng biến thể hoặc các cụm trạng thái cho lỗi nhỏ, nhưng phải thể hiện trực quan các thông báo quan trọng, không chỉ ghi chú rằng chúng tồn tại.

Dữ liệu minh họa hoàn toàn giả lập và nhất quán: “Chi nhánh Trung tâm” tại “12 Đường A, Phường B”; “Khoa Da liễu” thuộc chi nhánh này; “Phòng khám 101” thuộc khoa đó. Thêm “Phòng khám 102” chưa gán khoa. Không dùng dữ liệu bệnh nhân hoặc thông tin nhân viên thật.

## 11. Điểm chưa xác nhận và khác biệt với mã hiện tại

1. Chưa có design system riêng hoặc Figma đã duyệt để đối chiếu. Wordmark chữ và các thông số bố cục là đề xuất.
2. DTO `SetDepartmentRequest` hiện yêu cầu `type` (Loại khoa) và hỗ trợ `description`, trong khi Slide 4 không mô tả chúng. **Bản UI cơ sở trong brief này chưa chứa hai trường đó và chưa đủ để tạo khoa qua DTO hiện tại.** Cần nhóm xác nhận phạm vi và giá trị hợp lệ của Loại khoa trước khi bổ sung; không tự tạo danh mục loại khoa.
3. DTO Chi nhánh hỗ trợ `phone`, nhưng brief này giới hạn ở tên/địa chỉ theo Slide 4. Chưa đưa điện thoại vào UI.
4. Slide 4 xác nhận tạo phòng cần khoa hợp lệ và phòng có thể mất khoa sau thao tác xóa khoa. Chưa rõ có cho lưu sửa tên phòng trong khi vẫn chưa có khoa hay bắt buộc gán lại. Chưa thiết kế luồng gán lại hàng loạt hoặc tự động chuyển khoa.
5. Chưa xác nhận tìm kiếm, bộ lọc, sắp xếp, phân trang hoặc thống kê; không tự thêm vào bản cơ sở.

Các điểm này là ghi chú cho người review, không phải nội dung hiển thị cho Admin.

## 12. Ràng buộc và checklist review

Không thêm dashboard KPI, biểu đồ, quản lý bệnh nhân, hồ sơ bệnh án, lịch hẹn, nhân sự, thanh toán hoặc trang cài đặt. Không thêm email, giờ mở cửa, sức chứa, loại phòng, trạng thái hoạt động, mô tả hoặc trường nghiệp vụ ngoài phạm vi đã chỉ định. Không dùng gradient mạnh, glassmorphism, bóng nặng hoặc animation trang trí. Không tạo code triển khai hay kết nối backend trong nhiệm vụ thiết kế này.

- [ ] Ba module dùng chung một shell và hệ thống component.
- [ ] Chi nhánh có tên/địa chỉ bắt buộc, lỗi trùng tên, chặn xóa khi còn khoa/nhân viên.
- [ ] Khoa có chi nhánh bắt buộc, lỗi tên trùng trong cùng chi nhánh.
- [ ] Xóa khoa giữ phòng; phòng không còn khoa được hiển thị đúng.
- [ ] Phòng mới có tên/khoa bắt buộc; xóa bị chặn khi còn nhân viên.
- [ ] Có trạng thái không tìm thấy, không có quyền và lỗi dễ hiểu.
- [ ] Có tạo, sửa, chi tiết, xác nhận xóa, loading, empty và phản hồi thao tác.
- [ ] Bảng và form đọc tốt trên desktop; mobile giữ đủ thông tin và hành động.
- [ ] Các khác biệt backend ở mục 11 vẫn được ghi nhận, chưa bị coi là đã giải quyết.

## 13. Cách dùng trong Stitch

Dán nội dung brief này vào Google Stitch, kèm yêu cầu: “Hãy tạo bộ UI theo đặc tả này. Bắt đầu bằng shell chung và ba trang danh sách desktop, sau đó tạo chi tiết, form và các trạng thái bắt buộc. Giữ nguyên hệ thống thị giác giữa các lần tạo. Dùng tiếng Việt trên toàn bộ giao diện. Các điểm chưa xác nhận ở mục 11 chỉ là ghi chú review, không tự giải quyết bằng cách thêm tính năng.”

Nếu chia thành nhiều lượt, luôn nhắc giữ cùng shell, palette, typography và component; tạo lần lượt Chi nhánh → Khoa → Phòng → trạng thái chung → responsive. Review bằng checklist ở mục 12.
