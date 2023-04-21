

function listTbody(data) {

    let tbody = document.getElementById("tbody");
    tbody.textContent = '';
    for (let i of data) {
        let tr = document.createElement("tr");
        let th = document.createElement('th');

        th.textContent = `${i.orddate}`;
        th.setAttribute("scope", "row");

        // addordno
        tr.append(th);
        let ordno = document.createElement('td');
        ordno.textContent = `${i.ordno}`;
        tr.append(ordno);

        // add rec
        let rec = document.createElement('td');
        rec.textContent = `${i.rec}`;
        tr.append(rec);

        // add totalpr
        let totalpr = document.createElement('td');
        totalpr.textContent = `${i.totalpr}`;
        tr.append(totalpr);

        // add paysta

        let paysta = document.createElement('td');
        switch (i.paysta) {
            case 0: {
                paysta.textContent = "未付款";
                tr.append(paysta);
                break;
            }
            case 1: {
                paysta.textContent = "已付款";
                tr.append(paysta);
                break;
            }
            case 2: {
                paysta.textContent = "已退款";
                tr.append(paysta);
                break;
            }
            case 3: {
                paysta.textContent = "退款中";
                tr.append(paysta);
                break;
            }
        }


        // add ordsta
        let ordsta = document.createElement('td');

        switch (i.ordsta) {
            case 0: {
                ordsta.textContent = "未出貨";
                tr.append(ordsta);
                break;
            }
            case 1: {
                ordsta.textContent = "出貨中";
                tr.append(ordsta);
                break;
            }
            case 2: {
                ordsta.textContent = "訂單已完成";
                tr.append(ordsta);
                break;
            }

        }
        const table2 = document.getElementById("table2");
        let btnTd = document.createElement('td');
        let btn = document.createElement('button');
        btn.textContent = '查看詳情';
        btn.setAttribute("type", "button");
        btn.setAttribute("value", `${i.ordno}`);
        // btn.href = `/orderdetail/${i.ordno}`;
        btn.setAttribute("class", "btn btn-link rounded-pill m-2");
        btn.setAttribute("data-bs-toggle", "modal");
        btn.setAttribute("data-bs-target", "#checkMemoItemmordPatt");
        btn.addEventListener("click", function () {
            table2.classList.toggle('show');
            showTable2();
        });

        btnTd.append(btn);
        tr.append(btnTd);

        tbody.append(tr);
    }

}
// showTable2()

function showTable2() {
    fetch(`/memoitemordManage/listMemoitemordDetail?membno=${sessionStorage.getItem('membno')}`, {
        method: 'Get',
    }).then((response) => {
        response.json().then(data => {
            const table2 = document.querySelector('#table2');
            const bodydetail = data.map(obj => {
                const orderdetail = `<td>訂單內容</td>`;
                const ordno = obj[`ordno`];
                const ordnosell = `<td id = "${ordno}" class = "${ordno}">${ordno}</td>`;
                const rec = obj[`rec`];
                const reccell = `<td id = "${rec}" class = "${rec}">${rec}</td>`;
                const recaddr = obj[`recaddr`];
                const recaddrcell = `<td id = "${recaddr}" class = "${recaddr}">${recaddr}</td>`;
                const midate = obj[`midate`];
                const midatecell = `<td id = "${midate}" class = "${midate}">${midate}</td>`;
                const miname = obj[`miname`];
                const minamecell = `<td id = "${miname}" class = "${miname}">${miname}</td>`;
                const miqty = obj[`miqty`];
                const miqtycell = `<td id = "${miqty}" class = "${miqty}">${miqty}</td>`;
                const miprice = obj[`miprice`];
                const mipricecell = `<td id = "${miprice}" class = "${miprice}">${miprice}</td>`;
                const totalPr = obj[`totalPr`];
                const totalprcell = `<td id = "${totalPr}" class = "${totalPr}">${totalPr}</td>`;

                const paysta = obj[`paysta`];
                const paystaText = paysta === 0 ? '未付款' : paysta === 1 ? '已付款' : paysta === 2 ? '已退款' : paysta === 3 ? '退款中' : '';
                const paystacell = `<td id = "${paystaText}" class = "${paystaText}">${paystaText}</td>`;

                const ordsta = obj[`ordsta`];
                const ordstaText = ordsta === 0 ? '未出貨' : ordsta === 1 ? '出貨中' : ordsta === 2 ? '訂單已完成' : '';
                const ordstacell = `<td id = "${ordstaText}" class = "${ordstaText}">${ordstaText}</td>`;
                return `<tr>${orderdetail}${ordnosell}${reccell}${recaddrcell}${midatecell}${minamecell}${miqtycell}${mipricecell}${totalprcell}${paystacell}${ordstacell}</tr>`


            }).join('');

            table2.querySelector('#tbody2').innerHTML = bodydetail;

        });
    });
}

function showTable3() {
    const searchInput = document.getElementById('searchInput');
    const ordno = searchInput.value.trim();

    if (ordno === '') {
        Swal.fire({
            icon: 'error',
            text: '訂單編號不能為空',
        })
        return
    }
    if (!/^\d+$/.test(ordno)) {
        Swal.fire({
            icon: 'error',
            text: '訂單編號必必須是數字',
        })
        return

    }


    fetch(`/memoitemordManage/listMemoitemordDetailByOrderno?ordno=${ordno}`, {
        method: 'Get',
    }).then((response) => {
        response.json().then(data => {
            const table2 = document.querySelector('#table2');
            const bodydetail = data.map(obj => {
                const orderdetail = `<td>訂單內容</td>`;
                const ordno = obj[`ordno`];
                const ordnosell = `<td id = "${ordno}" class = "${ordno}">${ordno}</td>`;
                const rec = obj[`rec`];
                const reccell = `<td id = "${rec}" class = "${rec}">${rec}</td>`;
                const recaddr = obj[`recaddr`];
                const recaddrcell = `<td id = "${recaddr}" class = "${recaddr}">${recaddr}</td>`;
                const midate = obj[`midate`];
                const midatecell = `<td id = "${midate}" class = "${midate}">${midate}</td>`;
                const miname = obj[`miname`];
                const minamecell = `<td id = "${miname}" class = "${miname}">${miname}</td>`;
                const miqty = obj[`miqty`];
                const miqtycell = `<td id = "${miqty}" class = "${miqty}">${miqty}</td>`;
                const miprice = obj[`miprice`];
                const mipricecell = `<td id = "${miprice}" class = "${miprice}">${miprice}</td>`;
                const totalPr = obj[`totalPr`];
                const totalprcell = `<td id = "${totalPr}" class = "${totalPr}">${totalPr}</td>`;

                const paysta = obj[`paysta`];
                const paystaText = paysta === 0 ? '未付款' : paysta === 1 ? '已付款' : paysta === 2 ? '已退款' : paysta === 3 ? '退款中' : '';
                const paystacell = `<td id = "${paystaText}" class = "${paystaText}">${paystaText}</td>`;

                const ordsta = obj[`ordsta`];
                const ordstaText = ordsta === 0 ? '未出貨' : ordsta === 1 ? '出貨中' : ordsta === 2 ? '訂單已完成' : '';
                const ordstacell = `<td id = "${ordstaText}" class = "${ordstaText}">${ordstaText}</td>`;
                return `<tr>${orderdetail}${ordnosell}${reccell}${recaddrcell}${midatecell}${minamecell}${miqtycell}${mipricecell}${totalprcell}${paystacell}${ordstacell}</tr>`


            }).join('');

            table2.querySelector('#tbody2').innerHTML = bodydetail;

        });
    });
}



//載入網頁直接顯示
window.onload = function () {
    fetch(`/memoitemordManage/findByMemb?membno=${sessionStorage.getItem("membno")}`)
        .then((response) => response.json())
        .then((data) => {
            listTbody(data);
        });
    // showTable2();

};


const table3 = document.getElementById("table2");
const btn3 = document.getElementById('btn3');
btn3.addEventListener('click', () => {
    showTable3();
    table3.classList.toggle('show');
});



const table2 = document.getElementById("table2");
const btn = document.getElementById("btn");

btn.addEventListener('click', () => {
    table2.classList.toggle('show');
});


