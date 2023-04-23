// alert("111");

showTable();

function showTable() {
    fetch(`/schedule/findOrdbyMembno?membno=${sessionStorage.getItem('membno')}`, {
        method: 'GET',
    }).then((response) => {
        response.json().then(data => {
            const table = document.querySelector('#table');
            const bodyCells = data.map(obj => {
                const poDate = obj[`poDate`];
                const poDatecell = `<td id="${poDate}" class="cerNo">${poDate}</td>`;
                const pono = obj[`pono`];
                const ponocell = `<td id="${pono}" class="cerNo">${pono}</td>`;
                const dname = obj[`dname`];
                const dnamecell = `<td id="${dname}" class="cerNo">${dname}</td>`;
                const tprice = obj[`tprice`];
                const tpricell = `<td id="${tprice}" class="cerNo">${tprice}</td>`;

                const paysta = obj[`paysta`];
                const paystaText = paysta === 0 ? '未付款' : paysta === 1 ? '已付訂金' : paysta === 2 ? '已結款' : paysta === 3 ? '退款中' : '';
                const paystacell = `<td id = "${paystaText}" class = "${paystaText}">${paystaText}</td>`;

                const posta = obj[`posta`];
                const postaText = posta === 0 ? '協商中' : posta === 1 ? '已付款' : posta === 2 ? '已結款' : posta === 3 ? '已退款' : '';
                const postacell = `<td id = "${postaText}" class = "${postaText}">${postaText}</td>`;

                const searchbtn1 = `
                <td>
                <a href="planOrderDetail.html" class="list-item">
                <button type="button" class="searchbtn1">查詢方案詳情</button>
                </a;
                </td>`


                return `<tr>${poDatecell}${ponocell}${dnamecell}${tpricell}${paystacell}${postacell}${searchbtn1}</tr>`;




            }).join('');
            table.querySelector('tbody').innerHTML = bodyCells;


        });
    });
}