// alert("111");

// const form = document.querySelector('form');


const email = sessionStorage.getItem('email');
const mpic = sessionStorage.getItem('mpic');
const mname = sessionStorage.getItem('mname');
const msg = document.querySelector('#msg');
const btn2 = document.querySelector('#btn2');
const oPassword = document.querySelector('#oPassword');
const nPassword = document.querySelector('#nPassword');
const picture = document.querySelector('#picture');
const mnameForm = document.querySelector('#mname');
const emailForm = document.querySelector('#email');
const confirmPassword = document.querySelector('#confirmPassword');
const sex = document.querySelector('#sex');
const mobile = document.querySelector('#mobile');
const versta = sessionStorage.getItem('versta');
const btn3 = document.querySelector('#btn3');

confirmPassword.addEventListener('blur', () => {
    if (confirmPassword.value !== nPassword.value) {
        msg.textContent = "確認密碼輸入錯誤";
        msg.className = "error";
    } else {
        msg.textContent = "";
        msg.className = "";
    }
})



btn3.addEventListener('click', () => {
    window.location.href = 'index_Chian.html';
})



//預設照片
const imageBinartStr = atob(mpic);
let len = imageBinartStr.length;
const uint8Array = new Uint8Array(len);

for (let i = 0; i < len; i++) {
    uint8Array[i] = imageBinartStr.charCodeAt(i);
}

const blob = new Blob([uint8Array]);
document.querySelector('#preImage').src = URL.createObjectURL(blob);




//讀出選取照片
document.getElementById('picture').addEventListener('change', function () {
    // 取得選取的檔案
    const file = this.files[0];
    const preview = document.getElementById('preImage');

    // 如果有選擇檔案才執行
    if (file && file.type.match('image.*')) {
        // 創建 FileReader 物件
        const reader = new FileReader();

        // 當讀取完成時觸發
        reader.onload = function (e) {
            // 取得預覽圖片的 img 元素
            // const preview = document.getElementById('preview');

            // 設定圖片的 src 屬性為讀取的檔案資料
            preImage.src = e.target.result;

            // 顯示預覽圖片
            preImage.style.display = 'block';
        }

        // 讀取檔案資料
        reader.readAsDataURL(file);
    } else {
        preview.src = '';
        preview.style.display = 'none';
    }
});


function showEdit() {
    mnameForm.value = sessionStorage.getItem('mname');
    emailForm.value = sessionStorage.getItem('email');
    sex.value = sessionStorage.getItem('sex');
    mobile.value = sessionStorage.getItem('mobile');
    oPassword.value = sessionStorage.getItem('mpw');
    nPassword.value = sessionStorage.getItem('nPassword');
    confirmPassword.value = sessionStorage.getItem('confirmPassword')

}
window.onload = showEdit;


// 把欄位的值丟給DTO
function sendEdit() {
    console.log(emailForm.value);
    let profile = {
        membno: sessionStorage.getItem('membno'),
        mname: mnameForm.value,
        email: emailForm.value,
        sex: sex.value,
        mobile: mobile.value,
        mpw: nPassword.value,
        mpic: mpic,
        versta: versta
    };

    const pictureFile = picture.files[0];
    if (!pictureFile) {
        console.error("No picture selected");
        return;
    }

    console.log(emailForm.value);

    console.log(profile);

    const fileReader = new FileReader();
    fileReader.addEventListener('load', e => {
        const imageBase64 = btoa(e.target.result);
        const picture = document.querySelector('#picture');

        console.log("here");
        fetch("/memberEdit/updateMember", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                membno: sessionStorage.getItem('membno'),
                mname: mnameForm.value,
                email: emailForm.value,
                sex: sex.value,
                mobile: mobile.value,
                mpw: nPassword.value,
                mpic: imageBase64,
                versta: versta,
            }),
        })
            .then(response => response.json())
            // alert("111")
            .then(data => {
                sessionStorage.setItem("sex", data.sex);
                sessionStorage.setItem("email", data.email);
                sessionStorage.setItem("mname", data.mname);
                sessionStorage.setItem("mobile", data.mobile);
                sessionStorage.setItem("membno", data.membno);
                sessionStorage.setItem('mpw', data.mpw);
                sessionStorage.setItem('versta', data.versta);
                sessionStorage.setItem("mpic", data.mpic);
                // window.location.href = '../front/login2.html';
            })
            .then(() => {
                Swal.fire({
                    position: 'top-end',
                    icon: 'success',
                    title: '修改成功',
                    showConfirmButton: false,
                    timer: 1500
                })
            })
            .then(window.location.href = 'login2.html')

        // .then(() => {
        //     window.location.replace = '../front/login2.html';
        // })

    });
    fileReader.readAsBinaryString(pictureFile);
};

btn2.addEventListener('click', sendEdit);


 // Swal.fire({
            //     position: 'top-end',
            //     icon: '修改成功',
            //     title: '會員資料已修改',
            //     showConfirmButton: false,
            //     timer: 1500
            // })