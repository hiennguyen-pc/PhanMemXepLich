create database QL_BacSi
go
use QL_BacSi
go
create table BacSI(
	id char(10) primary key,
	TenBS nvarchar(50),
	NgaySinh nvarchar(50),
	GioiTinh nvarchar(50)
)
go
create table LichTruc(
	id int primary key identity(1,1),
	idBS char(10),
	NgayTruc nvarchar(50),
	PhongTruc int,
	CaTruc int,
	foreign key(idBS) references BacSI(id)
)

select *from BacSI
Insert into Bacsi
values('BS001',N'Nguyễn Minh Hiến','30/07/2000','Nam')
delete BacSI where id=''
insert into LichTruc
values('BS001','23','7','1')
go
create function fu_XepLich()
returns table
as
	return select LichTruc.id, TenBS,PhongTruc,CaTruc,NgayTruc from BacSI,LichTruc where BacSI.id=LichTruc.idBS
go
select *from dbo.fu_XepLich()
drop function fu_XepLich
select *from LichTruc
