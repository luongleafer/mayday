
# DrMayDay – Quy chuẩn thiết kế giao diện Frontend

## 1. Công nghệ sử dụng

| Công nghệ | Mục đích |
|---|---|
| React | Xây dựng giao diện |
| Vite | Khởi tạo và chạy dự án |
| JavaScript (JSX) | Phát triển Frontend |
| CSS Modules | Quản lý CSS theo component |
| CSS Variables | Quản lý các giá trị thiết kế chung |
| Lucide React | Biểu tượng giao diện |
| Inter | Font chữ chính |

## 2. Bảng màu và Typography

### Bảng màu

| Tên màu | Mã màu | Mục đích |
|---|---|---|
| Xanh chủ đạo | `#2C5EAD` | Nút chính, nhận diện thương hiệu |
| Xanh phụ | `#1591DC` | Liên kết, thao tác phụ |
| Xanh nhấn | `#4BB8FA` | Làm nổi bật |
| Xanh nhạt | `#C4E2F5` | Nền được chọn |
| Vàng kem | `#FFF9D2` | Thông tin cần lưu ý |
| Trắng | `#FFFFFF` | Nền chính |
| Nền phụ | `#F5F8FC` | Phân chia khu vực |
| Đường viền | `#E5EAF1` | Viền bảng và thẻ |
| Chữ chính | `#253449` | Nội dung chính |
| Chữ phụ | `#64748B` | Nội dung bổ sung |

### Typography

Font chữ chính: Inter.

| Thành phần | Kích thước | Độ đậm |
|---|---:|---:|
| Tiêu đề trang | 24px | 700 |
| Tiêu đề khu vực | 20px | 600 |
| Tiêu đề thẻ | 16px | 600 |
| Nội dung | 14px | 400 |
| Nhãn biểu mẫu | 14px | 500 |
| Chú thích | 12px | 400 |

Nguyên tắc: Ưu tiên nền trắng, sử dụng màu xanh cho nhận diện và thao tác quan trọng, đảm bảo chữ dễ đọc và có độ tương phản phù hợp.

## 3. Bố cục và thành phần giao diện

### Bố cục chính

Giao diện quản trị sử dụng ba khu vực:

- Sidebar: Thanh điều hướng bên trái.
- Header: Thanh thông tin phía trên.
- Main Content: Khu vực hiển thị nội dung chức năng.

Kích thước tham khảo:

| Thành phần | Kích thước |
|---|---:|
| Sidebar | 240px |
| Header | 64–72px |
| Khoảng cách nội dung | 24px |
| Padding trong Card | 20–24px |
| Bo góc Button / Input | 8px |
| Bo góc Card | 12px |
| Bo góc Modal | 16px |

### Thành phần dùng chung

- Button
- Input
- Select
- Search
- Table
- Pagination
- Modal
- Confirm Dialog
- Toast Notification
- Badge
- Sidebar
- Header

Ưu tiên tái sử dụng component để đảm bảo giao diện đồng nhất giữa các chức năng.

## 4. Quy chuẩn thiết kế và trải nghiệm người dùng

Các thành viên cần thống nhất cách hiển thị những trạng thái:

- Loading: Đang tải dữ liệu.
- Empty: Chưa có dữ liệu.
- Success: Thao tác thành công.
- Error: Thao tác thất bại.
- Validation: Dữ liệu không hợp lệ.
- Confirmation: Xác nhận trước khi xóa hoặc thực hiện thao tác quan trọng.
- Disabled: Thành phần không thể thao tác.

### Responsive

Ưu tiên Desktop và Laptop, đồng thời đảm bảo giao diện không bị tràn nội dung trên màn hình nhỏ.

| Thiết bị | Chiều rộng tham khảo |
|---|---:|
| Desktop | 1440px |
| Laptop | 1280px |
| Tablet | 768px |
| Mobile | 375px |

### Nguyên tắc chung

- Giao diện đơn giản, dễ quan sát và sử dụng.
- Các chức năng có cách bố trí và thao tác thống nhất.
- Thông báo cho người dùng bằng ngôn ngữ dễ hiểu.
- Không hiển thị trực tiếp lỗi kỹ thuật từ Backend.

## 5. Quy chuẩn Figma và phát triển Frontend

### Cấu trúc Figma đề xuất

```text
DrMayDay
│
├── 01 - Quy chuẩn chung
│   ├── Bảng màu
│   ├── Typography
│   └── Spacing
│
├── 02 - Thành phần dùng chung
│   ├── Button
│   ├── Input
│   ├── Table
│   ├── Modal
│   └── Navigation
│
└── 03 - Giao diện chức năng
    ├── Đăng nhập
    ├── Dashboard
    ├── Quản lý chi nhánh
    ├── Quản lý khoa / phòng ban
    └── Quản lý phòng chức năng
```

### Nguyên tắc phát triển

- Tuân thủ bảng màu và thiết kế đã thống nhất.
- Tái sử dụng các component dùng chung.
- Sử dụng CSS Modules và CSS Variables.
- Không tự ý thay đổi cấu trúc hoặc công nghệ Frontend khi chưa thống nhất với nhóm.
- Kiểm tra giao diện trước khi tạo Pull Request.

---
