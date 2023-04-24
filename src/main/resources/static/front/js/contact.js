

// const aboutusupdate = document.querySelector('#aboutusupdate');
// const aboutusduty = document.querySelector('#aboutusduty');
// const aboutushistory = document.querySelector('#aboutushistory');
const aboutusaddress = document.querySelector('#aboutusaddress');
const aboutusmobile = document.querySelector('#aboutusmobile');
const aboutusemail = document.querySelector('#aboutusemail');


// aboutusupdate.addEventListener('click', (event) => {
//     checkform(event);
//     let aboutusinfos = [{
//         "infono": 16001,
//         "infoname": "公司使命",
//         "infocon": aboutusduty.value
//     },
//     {
//         "infono": 16002,
//         "infoname": "公司歷史",
//         "infocon": aboutushistory.value
//     },
//     {
//         "infono": 16003,
//         "infoname": "公司地址",
//         "infocon": aboutusaddress.value
//     }, {
//         "infono": 16005,
//         "infoname": "連絡電話",
//         "infocon": aboutusmobile.value
//     }, {
//         "infono": 16006,
//         "infoname": "公司信箱",
//         "infocon": aboutusemail.value
//     }]

//     for (let i of aboutusinfos) {
//         fetch('/info/update', {
//             method: 'POST',
//             headers: { 'Content-Type': 'application/json' },
//             body: JSON.stringify(i),
//         })
//     }

// });


  const form = document.getElementById('contact-form');
  
  form.addEventListener('submit', function(event) {
    event.preventDefault(); // 防止表單預設提交行為
    
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const subject = document.getElementById('subject').value;
    const message = document.getElementById('message').value;
    
    const body = `姓名：${name}%0D%0A信箱：${email}%0D%0A主旨：${subject}%0D%0A內容：${message}`;
    
    window.open(`mailto:shinjinliao@gmail.com?subject=${subject}&body=${body}`);
  });



fetch("/info/findall")
    .then(response => response.json())
    .then(data => {
        for (let i of data) {
            switch (i.infono) {
                // case 16001: {
                //     // aboutusduty.value = i.infocon;
                //     aboutusduty.innerHTML = `<p class="mb-4">${i.infocon}</p>`
                //     break;
                // }
                // case 16002: {
                //     // aboutushistory.value = i.infocon;
                //     // document.querySelector('#test2').innerHTML = `<p class="mb-4">${i.infocon}</p>`
                //     aboutushistory.innerHTML = `<p class="mb-4">${i.infocon}</p>`
                   
                //     break;
                // }
                case 16003: {
                    // aboutusaddress.value = i.infocon;
                    aboutusaddress.innerHTML = `<p class="mb-2">地址</p>
                    <h3 class="mb-0">${i.infocon}</h3>`
                    break;
                }
                case 16005: {
                    // aboutusmobile.value = i.infocon;
                    aboutusmobile.innerHTML = `<p class="mb-2">聯絡資訊</p>
                    <h3 class="mb-0">${i.infocon}</h3>`
                    break;
                }
                case 16006: {
                    // aboutusemail.value = i.infocon;
                    aboutusemail.innerHTML = `<p class="mb-2">信箱</p>
                    <h3 class="mb-0">${i.infocon}</h3>`
                    break;
                }
            }
        }
    })

