alert("你好帥");


// 產生Tbody
function listTbody(data) {
    let tbody = document.getElementById("tbody");

    tbody.textContent = '';

    for (let i of data) {
        let tr = document.createElement("tr");
        let th = document.createElement('th');
        let image = document.createElement('img');
        th.textContent = `${i.membno}`;
        th.setAttribute("scope", "row");

        tr.append(th);
        let mname = document.createElement('td');
        mname.textContent = `${i.mname}`;
        tr.append(mname);

        // add mpic
        let mpic = document.createElement('td');
        image.src = i.mpic;   //`${i.mpic}`
        image.id = 'image';
        mpic.append(image);
        tr.append(mpic);

        // add mpw 
        let mpw = document.createElement('td');
        mpw.textContent = i.mpw;
        tr.append(mpw);

        // add email
        let email = document.createElement('td');
        email.textContent = i.email;
        tr.append(email);

        // add mobile
        let mobile = document.createElement('td');
        mobile.textContent = i.mobile;
        tr.append(mobile);

        // add sex
        let sex = document.createElement('td');
        switch (i.sex) {
            case 0: {
                sex.textContent = '女性';
                tr.append(sex);
                break;
            }
            case 1: {
                sex.textContent = '男性';
                tr.append(sex);
                break;
            }
        }

        let versta = document.createElement('td');
        switch (i.versta) {
            case 0: {
                versta.textContent = "啟用";
                tr.append(versta);
                break;
            }
            case 1: {
                versta.textContent = "停權";
                tr.append(versta);
                break;
            }
        }
        let btntd = document.createElement("td");
        let btn = document.createElement("button");
        btn.textContent = "修改";
        btn.setAttribute("type", "button");
        btn.setAttribute("value", `${i.membno}`);
        btn.setAttribute("class", "btn btn-link rounded-pill m-2");
        btn.setAttribute("data-bs-toggle", "modal");
        btn.setAttribute("data-bs-target", "#addMembPatt");
        btn.addEventListener("click", function () {
            fetch(`/memb/findById?membno=${i.membno}`)
                .then((response) => response.json())
                .then((data) => {
                    document.getElementById("membno").value = data.membno;
                    document.getElementById("mname").value = data.mname;
                    document.getElementById("mpw").value = data.mpw;
                    document.getElementById("email").value = data.email;
                    document.getElementById("mobile").value = data.mobile;
                    document.getElementById("sex").value = data.sex;
                    document.getElementById("versta").value = data.versta;

                    let mpic = document.getElementById("mpic");
                    mpic.innerHTML = "";
                    let image = document.createElement('img');


                    const imageBinartStr = atob(mpic);
                    let len = imageBinartStr.length;
                    const uint8Array = new Uint8Array(len);

                    for (let i = 0; i < len; i++) {
                        uint8Array[i] = imageBinartStr.charCodeAt(i);
                    }
                    const blob = new Blob([uint8Array]);

                    image.src = URL.createObjectURL(blob);
                    image.alt = data.mname;
                    image.style.maxWidth = '100px';
                    image.style.maxHeight = '100px';
                    mpic.appendChild(image);
                });
        });

        btntd.append(btn);
        tr.append(btntd);

        tbody.append(tr);


    }
}
//網頁載入直接顯示資料
window.onload = function () {
    fetch("/memb/getAllMember")
        .then((response) => response.json())
        .then((data) => {
            listTbody(data);
        });
};