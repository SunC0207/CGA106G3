// alert("你好帥")
showTable();

function showTable() {
    fetch(`/schedule/detailByMembno?membno=${sessionStorage.getItem('membno')}`, {
        method: 'GET',
    }).then((response) => {
        response.json().then(data => {
            const table = document.querySelector('#table');
            const rows = data.map(obj => {
                const poDate = obj[`poDate`];
                const poNo = obj[`poNo`];
                const relName = obj[`relName`];
                const dname = obj[`dname`];
                const dbirth = obj[`dbirth`];
                const ddate = obj[`ddate`];
                const iname = obj[`iname`];
                const iprice = obj[`iprice`];
                const date = obj[`date`];
                const locName = obj[`locName`];
                const totalPr = obj[`totalPr`];
                const paySta = obj[`paySta`];
                const paystaText = paySta === 0 ? '未付款' : paySta === 1 ? '已付訂金' : paySta === 2 ? '已結款' : paySta === 3 ? '退款中' : '';
                const poSta = obj[`poSta`];
                const poStaText = poSta === 0 ? '協商中' : poSta === 1 ? '已建案' : poSta === 2 ? '取消' : '';

                console.log(data);

                return `
                
                <tr>
                <td class="td1">方案建立日期poDate</td>
                <td class="td1" id="">${poDate}</td>
                 </tr>
            <tr>
                <td class="td1">訂單編號poNo</td>
                <td class="td1" id="">${poNo}</td>
            </tr>
            <tr>
                <td class="td1">方案名稱relName</td>
                <td class="td1" id="">${relName}</td>
            </tr>
            <tr>
                <td class="td1">往生者姓名dName</td>
                <td class="td1" id="">${dname}</td>
            </tr>
            <tr>
                <td class="td1">往生者出生日期dBirth</td>
                <td class="td1" id="">${dbirth}</td>
            </tr>
            <tr>
                <td class="td1">往生者往生日期dDate</td>
                <td class="td1" id="">${ddate}</td>
            </tr>
            <tr>
                <td class="td1">流程日期date</td>
                <td class="td1" id="">${date}</td>
            </tr>
            <tr>
                <td class="td1">細項名稱iName</td>
                <td class="td1" id="">${iname}</td>
            </tr>
            <tr>

                <td class="td1">細項價格iPrice</td>
                <td class="td1" id="">${iprice}</td>
            </tr>
            <tr>
                <td class="td1" id="">場地locName</td>
                <td>${locName}</td>
            </tr>
            <tr>
                <td class="td1" id="">總價totalPr</td>
                <td class="td1">${totalPr}</td>
            </tr>
            <tr>
                <td class="td1" id="">付款狀態paySta</td>
                <td class="td1">${paystaText}</td>
            </tr>
            <tr>
                <td class="td1" id="">訂單狀態poSta</td>
                <td class="td1">${poStaText}</td>
            </tr>`;
            }).join('');

            const tableContent = ` 
            <thead>
                <tr>
                    <th class="th1">訂單詳情</th>
                     <th class="th1">內容</th>
                 </tr>
            </thead>

            <tbody>
            ${rows}
            
            </tbody>
            `;
            table.innerHTML = tableContent;



        });
    });
}