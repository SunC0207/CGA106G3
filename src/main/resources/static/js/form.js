function validateEmail(aboutusemail) {
    const pattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;  // 正則表達式
    return pattern.test(aboutusemail);
}

function isValidPhoneNumber(aboutusmobile) {
    const regex = /^((0\d{1,2}-\d{6,8})(#\d{1,5})?|(09\d{8}))$/;
    const cleanedMobile = aboutusmobile.replace(/\D/g, ''); // 去掉非數字的字符
    return regex.test(cleanedMobile);
}

function checkform(event) {
    const aboutusaddress = document.querySelector(`#aboutusaddress`).value;
    const aboutusemail = document.querySelector("#aboutusemail").value;
    const aboutusmobile = document.querySelector("#aboutusmobile").value;

    if (aboutusaddress == "") {
        alert("請輸入正確的地址");
        aboutusupdate.preventDefault();
    } else if (!isValidPhoneNumber(aboutusmobile)) {
        alert("請輸入正確的電話號碼")
        event.preventDefault();
    } else if (!validateEmail(aboutusemail)) {
        alert("請輸入正確的email")
        event.preventDefault();
    } else {
        swal("新增成功!", "You clicked the button!", "success")

    }
}



const aboutusupdate = document.querySelector('#aboutusupdate');
const aboutusduty = document.querySelector('#aboutusduty');
const aboutushistory = document.querySelector('#aboutushistory');
const aboutusaddress = document.querySelector('#aboutusaddress');
const aboutusmobile = document.querySelector('#aboutusmobile');
const aboutusemail = document.querySelector('#aboutusemail');


aboutusupdate.addEventListener('click', (event) => {
    checkform(event);
    let aboutusinfos = [{
        "infono": 16001,
        "infoname": "公司使命",
        "infocon": aboutusduty.value
    },
    {
        "infono": 16002,
        "infoname": "公司歷史",
        "infocon": aboutushistory.value
    },
    {
        "infono": 16003,
        "infoname": "公司地址",
        "infocon": aboutusaddress.value
    }, {
        "infono": 16005,
        "infoname": "連絡電話",
        "infocon": aboutusmobile.value
    }, {
        "infono": 16006,
        "infoname": "公司信箱",
        "infocon": aboutusemail.value
    }]

    for (let i of aboutusinfos) {
        fetch('/info/update', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(i),
        })
    }

});

fetch("/info/findall")
    .then(response => response.json())
    .then(data => {
        for (let i of data) {
            switch (i.infono) {
                case 16001: {
                    aboutusduty.value = i.infocon;
                    break;
                }
                case 16002: {
                    aboutushistory.value = i.infocon;
                    break;
                }
                case 16003: {
                    aboutusaddress.value = i.infocon;
                    break;
                }
                case 16005: {
                    aboutusmobile.value = i.infocon;
                    break;
                }
                case 16006: {
                    aboutusemail.value = i.infocon;
                    break;
                }
            }
        }
    })

