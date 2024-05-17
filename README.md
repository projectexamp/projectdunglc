
 -- datnd 15
               case when p_user is not null and exists(select * from cis_request_email xx where xx.cis_no=CIS_REQUEST.cis_no and xx.created_user=p_user) then 1
                
                  when p_user is null and exists(select * from cis_request_email xx where xx.cis_no=CIS_REQUEST.cis_no and xx.created_user=CIS_REQUEST.MAKER) then 1
                 
                 else 0 end as FLAG,

1 Kiểm tra Khách hàng từng hỏi tin 
a. Thể nhân S11A- 159696872
b. Pháp nhân - S10A - DKKD - 1125K
2.Kieerm tra khach hang chua tung hoi tin
- S10A - DKKD:09
3.Kiem tra Respone tra ve day du cac truong
a. Thể nhân - 159696872
b. Pháp nhân - S10A - DKKD - 1125K
4. Kiem tra tra cuu thong tin nhieu san pham
a. the nhan - R11A,S11A, CMT: 172988033
b. phap nhan - S10A, R14.DN, MST: 912381000111
5.Kiem tra hoi tin cung luc nhieu SP

- Trường hợp hiển thị cảnh báo warning: S11A- 159696872


"flag": "1",
    "maker": "thomnt2",
    "user": "thomnt2",
    "param1": "cicCode:4031840274;customerType:2;customerName:Nguy?n Th? Hoài An;productCode:S11A;customerType:1;idNo:123456789;address:Tru?ng MN B?n Ngo?i, Ð?i T?, TN:registerNo:123456789; taxCode:1233"



{
    "user":"thomnt2",
    "channel": "PCB",
    "msgId": "JmtTbQxWQCymHxBYJxvRvuPzlbgBOAUsgDDITmNUSBVtOzmATsivqxhvOZVqRSOwFCubk",
    "productCode": "PCB_CN",
    "customerName": "Tran thi Linh dan",
    "gender": "M",
    "dob": "09/04/1990",
    "idNo": "782236129",
    "docType": "",
    "registerNo": "",
    "address": "Hà Noi",
    "borrower": "1",
    "pcbCode": "",
    "operationType": "31",
    "amountFinCapital": "20000",
    "totalInstalment": "2",
    "creditLimit": 10000,
    "customerType": "2",
    "ccy": "VND",
    "countryOfBirth": "VN",
    "payPeriodicity": "M"
}
