

// function checkform() {
//     const username = document.forms["registerForm"]["username"].value;
//     const password = document.forms["registerForm"]["password"].value;
//     const confirmpassword = document.forms["registerForm"]["confirmpassword"].value;
//     const email = document.forms["registerForm"]["email"].value;
//     const mobile = document.forms["registerForm"]["mobile"].value;

//     if (username == "") {
//         alert("使用者名稱為必填欄位");
//         event1.preventDefault();
//         // return false;
//     }
//     // 密碼長度6~12位數
//     if (password.length < 6 || password.length > 12) {
//         alert("密碼長度應在6到12個字符之間");
//         event1.preventDefault();
//         // return false;
//     }

//     // 密碼是否與帳號相同
//     if (confirmpassword != password) {
//         alert("確認密碼與密碼不相符");
//         // return false;
//         event1.preventDefault();
//     }
//     // email 格式
//     if (!validateEmail(email)) {
//         alert("email格式錯誤")
//         event1.preventDefault();
//     }



//     if (!isValidPhoneNumber(mobile)) {
//         alert("請輸入正確的手機號碼")
//         event1.preventDefault();
//     }

// }





// 驗證碼文字生成
function generateCaptcha() {
    const captchaChars = '0123456789';
    const captchaLength = 4;
    let captcha = '';
    for (let i = 0; i < captchaLength; i++) {
        captcha += captchaChars[Math.floor(Math.random() * captchaChars.length)];
    }
    return captcha;
}

// 更新驗證碼圖片
function refreshCaptchaImg() {
    const captchaImg = document.getElementById('captcha-img');
    const captcha = generateCaptcha();
    captchaImg.src = `data:image/svg+xml,<svg xmlns='http://www.w3.org/2000/svg' width='100' height='30'><text x='0' y='20' font-size='20' font-weight='bold'>${encodeURIComponent(captcha)}</text></svg>`;
    document.getElementById('captcha').value = captcha;
}

// 載入時先產生一個驗證碼
const captcha = generateCaptcha();
document.getElementById('captcha-img').src = `data:image/svg+xml,<svg xmlns='http://www.w3.org/2000/svg' width='100' height='30'><text x='0' y='20' font-size='20' font-weight='bold'>${captcha}</text></svg>`;
document.getElementById('captcha').value = captcha;


const captchaInput = document.getElementById('captcha1');

captchaInput.addEventListener('blur', () => {
    const captcha = document.getElementById('captcha').value;
    const captchaInputt = document.getElementById('captcha1').value;
    if (captcha != captchaInputt) {
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: '錯誤',
            text: '驗證碼輸入錯誤',
        });
    }
})




// 
// 當 input[type = "file"] 元素的值改變時觸發


function validateEmail(email) {
    const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;  // 正則表達式
    return pattern.test(email);
}

function isValidTWPhone(phone) {
    // 驗證台灣手機號碼格式：09xx-xxx-xxx 或 09xxxxxxxx
    const phoneRegex = /^09\d{8}$|^09\d{2}-\d{3}-\d{3}$/;
    return phoneRegex.test(phone);
}

// document.querySelector('#registerForm').addEventListener('submit', checkform);

document.getElementById('avatar').addEventListener('change', function () {
    // 取得選取的檔案
    const file = this.files[0];
    const preview = document.getElementById('preview');

    // 如果有選擇檔案才執行
    if (file && file.type.match('image.*')) {
        // 創建 FileReader 物件
        const reader = new FileReader();

        // 當讀取完成時觸發
        reader.onload = function (e) {
            // 取得預覽圖片的 img 元素
            // const preview = document.getElementById('preview');

            // 設定圖片的 src 屬性為讀取的檔案資料
            preview.src = e.target.result;

            // 顯示預覽圖片
            preview.style.display = 'block';
        }

        // 讀取檔案資料
        reader.readAsDataURL(file);
    } else {
        preview.src = '';
        preview.style.display = 'none';
    }
});


// document.querySelector('#registerForm').addEventListener('submit', checkform);

const btnregister = document.querySelector('#btnregister');
const msg = document.querySelector('#msg');
const username = document.querySelector('#username');
const password = document.querySelector('#password');
const confirmpassword = document.querySelector('#confirmpassword');
const gender = document.querySelector('#gender');
const mobile = document.querySelector('#mobile');
const email = document.querySelector('#email');
const inputs = document.querySelectorAll('input');

const emailInput = document.getElementById('email');

emailInput.addEventListener('blur', () => {
    const email = emailInput.value;
    if (validateEmail(email)) {

    } else {
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: '錯誤',
            text: '請輸入有效的電子郵件地址',
        });
    }
});

confirmpassword.addEventListener('blur', () => {
    if (confirmpassword.value !== password.value) {
        msg.textContent = "確認密碼輸入錯誤";
        msg.className = "error";
    } else {
        msg.textContent = "";
        msg.className = "";
    }
})

const mobileInput = document.querySelector('#mobile');
mobileInput.addEventListener('blur', () => {
    const mobile = mobileInput.value;
    if (isValidTWPhone(mobile)) {

    } else {
        Swal.fire({
            position: 'center',
            icon: 'error',
            title: '錯誤',
            text: '請輸入正確的手機號碼',
        });

    }
})



btnregister.addEventListener('click', () => {

    const img = document.querySelector('#avatar');

    msg.textContent = '';
    const fileReader = new FileReader();
    fileReader.addEventListener('load', e => {
        const imageBase64 = btoa(e.target.result);
        fetch('/memberRegister/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                mname: username.value,
                mpw: password.value,
                sex: gender.value,
                mobile: mobile.value,
                mpic: imageBase64,
                email: email.value,
                versta: 0

            }),
        })

            .then(resp => resp.json())
            .then(body => {
                const { successful, message } = body;
                if (successful) {
                    for (let input of inputs) {
                        input.disable = true;
                    }
                    sessionStorage.setItem("email", body.email);
                    sessionStorage.setItem("mname", body.mname);
                    sessionStorage.setItem("mobile", body.mobile)
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: '註冊成功',
                        showConfirmButton: false,
                        timer: 1500
                    })
                        .then(() => {
                            msg.className = 'info';
                            window.location.href = '../front/login2.html';
                        });

                }

                else {
                    Swal.fire({
                        position: 'center',
                        icon: 'error',
                        title: '註冊失敗',
                        text: message,
                    })
                }

            })
    });
    fileReader.readAsBinaryString(img.files[0]);


    // if (img.files && img.files[0]) {

    //     fileReader.readAsBinaryString(img.files[0]);
    // } else {
    //     console.log('請選擇要上傳的文件');
    // }

});




// img.addEventListener('change', () => {
//     const img = document.querySelector('#preview');
//     const file = img.files[0];
//     if (file) {
//         img.src = URL.createObjectURL(file);
//         // preview.style.display = "block";
//     }
// });




