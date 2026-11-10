# Thống nhất cách đặt tên trong code backend

## Các quy tắc cơ bản

### ORM

Tên lớp Entity và tên các trường: tiếng Việt không dấu, camelCase hoặc PascalCase:

- Ví dụ: trong database có bảng `hosobenhan` lưu trữ hồ sơ bệnh án, bảng này tương ứng với lớp Entity có tên
  `HoSoBenhAn`
- Tên các cột của bảng hiện được viết theo snake_case như `ma_ho_so` chuyển thành `maHoSo` trong tên thuộc tính của lớp
- Quy tắc trên sẽ được tự động áp dụng trong quá trình ánh xạ giữa lớp trong code và bảng trong cơ sở dữ liệu

Như vậy, thay vì viết

```java
//...
@Column(name = "ma_ho_so")
Long maHoSo;
//...
```

Thì chỉ cần viết

```java
//...
Long maHoSo;
//...
```

Hibernate sẽ tự động map trường `maHoSo` ứng với cột `ma_ho_so`.
Tên các cột tương ứng trong các annotation như `@OneToOne` hay `@ManyToOne` cũng sử dụng chung quy tắc này.

Khi tên cột có chứa chữ số hoặc một số ngoại lệ đặc biệt gây lỗi cho quá trình tự động chuyển tên của Hibernate,
cần ghi rõ trong thuộc tính `name` của `@Column`. Ví dụ

```java

@Column(name = "phu_mach_1_tuan_gan_day")
Boolean phuMach1TuanGanDay;
```

Các lớp/hàm getter, setter, builder,... theo convention của OOP hoặc Spring thì giữ nguyên theo convention đó:

- Lớp repository cho `HoSoBenhAn` là `HoSoBenhAnRepository`
- Hàm getter cho thuộc tính `maHoSo` là `getMaHoSo()`

### Các phương thức xử lý request trong controller (Handler methods)

Chuyển tên snake_case của API tương ứng trong bộ slide yêu cầu nghiệp vụ thành camelCase, ví dụ:
với yêu cầu API có tên `set_branch` thì tên hàm tương ứng là `setBranch()`.

Nếu hàm trong controller tương ứng với hàm trong service, đặt tên hàm trong service theo nguyên tắc trên.

### Thuật ngữ chuyên ngành, nghiệp vụ

Các thuật ngữ chuyên ngành y tế có thể giữ nguyên tiếng Việt hoặc sử dụng tiếng Anh, tra cứu trong `DICTIONARY.md`.
Ví dụ, các hàm xử lý hồ sơ bệnh án có thể dùng `HoSoBenhAn` hoặc `MedicalRecord` trong tên.

### Các trường hợp còn lại

Thống nhất sử dụng tiếng Anh



