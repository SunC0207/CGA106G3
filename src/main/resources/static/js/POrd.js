new Vue({
    el: '#POrd',
    data() {
        return {
            searchTerm: '',
            orders: [],
            order: '',
        }
    },
    mounted() {
        fetch('/schedule/getAll', {
            method: 'GET',
        }).then(response => response.json())
            .then((data) => {
                this.orders = data;
            });
    },
    computed: {
        regex() {
            return new RegExp(this.searchTerm, "i");
        },
    },
    watch: {
        orderDetails(newVal, oldVal) {
            console.log(this.orderDetails)
            if (newVal !== oldVal) {
                $('#detailModal').modal('show');
            }
        }
    },
    methods: {
        updatePOrd(order) {
            fetch(`/schedule/update/${order.pono}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(order)
            }).then(response => {
                if (response.ok) {
                    Swal.fire({
                        icon: 'success',
                        title: '儲存成功',
                        text: '',
                        confirmButtonText: 'OK',
                    }).then(() => {
                        location.reload();
                    })

                } else {
                    Swal.fire({
                        icon: 'error',
                        title: '儲存失敗',
                        showConfirmButton: false,
                        timer: 1500
                    }).then(() => {
                        location.reload();
                    })
                }
            })
        },
        showUpdateModal(order) {
            $('#updateModal').modal('show');
            this.order = order
        },
        showDetail(order) {
            // $('#detailModal').modal('show');
            localStorage.setItem('pono', order.pono)
            location.href='./PODetail.html'
            // fetch(`/schedule/detailJoin?pono=${order.pono}`, {
            //     method: 'GET',
            // })
            //     .then(response => response.json())
            //     .then((data) => {
            //         this.orderDetails = data;
            //     })
        }

        // showDetail(order) {
        //     $('#detailModal').modal('show');
        //     fetch(`/schedule/detailJoin?pono=${order.pono}`, {
        //         method: 'GET',
        //     }).then(response => response.json())
        //         .then((data) => {
        //             let tableRows = '';
        //             data.forEach(detail => {
        //                 tableRows += `<tr>
        //                                 <td>${detail.poDate}</td>
        //                                 <td>${detail.poNo}</td>
        //                                 <td>${detail.relName}</td>
        //                                 <td>${detail.dname}</td>
        //                                 <td>${detail.dbirth}</td>
        //                                 <td>${detail.ddate}</td>
        //                                 <td>${detail.date}</td>
        //                                 <td>${detail.iname}</td>
        //                                 <td>${detail.iprice}</td>
        //                                 <td>${detail.locName}</td>
        //                                 <td>${detail.totalPr}</td>
        //                                 <td>${detail.paySta}</td>
        //                                 <td>${detail.poSta}</td>
        //                                 <td style="display: none;">${detail.detailNo}</td>
        //                                 <td style="display: none;">${detail.itemNo}</td>
        //                                 <td style="display: none;">${detail.locNo}</td>
        //                                 <td style="display: none;">${detail.iname}</td>
        //                               </tr>`;
        //             });
        //             document.querySelector('#modalBody').innerHTML = tableRows;
        //         });

        // },

    },
})




// $(document).ready(() => {
//     const pageSize = 5;
//     let currentPage = 1;

//     // 初始化時顯示第一頁
//     showTable(currentPage);

//     // 監聽分頁按鈕
//     $(document).on('click', '.page-link', function () {
//         const pageNum = parseInt($(this).data('page'));
//         if (pageNum !== currentPage) {
//             showTable(pageNum);
//         }
//     });

//     // 顯示表格
//     function showTable(pageNum) {
//         currentPage = pageNum;
//         fetch(`/schedule/findAll?page=${pageNum}&size=${pageSize}`, {
//             method: 'GET',
//         }).then((response) => {
//             response.json().then(data => {
//                 const table = document.querySelector('#table');
//                 const bodyCells = data.content.map(obj => {
//                     const pono = obj['pono'];
//                     const ponoCell = `<td id="${pono}" class="cerno">${pono}</td>`;
//                     const membno = obj['membno'];
//                     const membnoCell = `<td id="${membno}">${membno}</td>`;
//                     const empno = obj['empno'];
//                     const empnoCell = `<td id="${empno}">${empno}</td>`;
//                     const tprice = obj['tprice'];
//                     const tpriceCell = `<td id="${tprice}">$${tprice}</td>`;
//                     const poDate = obj['poDate'];
//                     const poDateCell = `<td id="${poDate}">${poDate}</td>`;
//                     const posta = obj['posta'];
//                     const postaOptions = `
//                     <select id="posta-${pono}" class="form-select">
//                     <option value="0" ${posta === 0 && 'selected'}>協商中</option>
//                     <option value="1" ${posta === 1 && 'selected'}>已建案</option>
//                     <option value="2" ${posta === 2 && 'selected'}>取消</option>
//                     </select>`;
//                     const postaCell = `<td>${postaOptions}</td>`;
//                     const paysta = obj['paysta'];
//                     const paystaOptions = `
//                     <select id="paysta-${pono}" class="form-select">
//                     <option value="0" ${paysta === 0 && 'selected'}>未付款</option>
//                     <option value="1" ${paysta === 1 && 'selected'}>已付訂金</option>
//                     <option value="2" ${paysta === 2 && 'selected'}>已結清</option>
//                     <option value="3" ${paysta === 3 && 'selected'}>已退款</option>
//                     </select>`;
//                     const paystaCell = `<td>${paystaOptions}</td>`;
//                     const poType = obj['poType'];
//                     const poTypeOptions = `
//                     <select id="poType-${pono}" class="form-select">
//                     <option value="0" ${poType === 0 && 'selected'}>定型化契約</option>
//                     <option value="1" ${poType === 1 && 'selected'}>生前契約</option>
//                     </select>`;
//                     const poTypeCell = `<td>${poTypeOptions}</td>`;
//                     const dname = obj['dname'];
//                     const dnameCell = `<td id="${dname}">${dname}</td>`;
//                     const dbirth = obj['dbirth'];
//                     const dbirthCell = `<td id="${dbirth}">${dbirth}</td>`;
//                     const ddate = obj['ddate'];
//                     const ddateCell = `<td id="${ddate}">${ddate}</td>`;
//                     return `<tr>${ponoCell}${membnoCell}${empnoCell}${tpriceCell}${poDateCell}${dnameCell}${dbirthCell}${ddateCell}${postaCell}${paystaCell}${poTypeCell}</tr>`;
//                 }).join('');
//                 table.querySelector('tbody').innerHTML = bodyCells;

//                 // 更新分頁按鈕
//                 const pagination = document.querySelector('.pagination');
//                 const totalPage = data.totalPages;
//                 const startPage = Math.max(1, pageNum - 1);
//                 const endPage = Math.min(totalPage, pageNum + 1);
//                 let pageButtons = '';
//                 for (let i = startPage; i <= endPage; i++) {
//                     pageButtons += `<li class="page-item ${i === pageNum ? 'active' : ''}"><a class="page-link" href="#" data-page="${i}">${i}</a></li>`;
//                 }
//                 pagination.innerHTML = `
//                     <li class="page-item ${pageNum === 1 ? 'disabled' : ''}">
//                         <a class="page-link" href="#" data-page="${pageNum - 1}" aria-label="Previous">
//                             <span aria-hidden="true">&laquo;</span>
//                             <span class="sr-only">Previous</span>
//                         </a>
//                     </li>
//                     ${pageButtons}
//                     <li class="page-item ${pageNum === totalPage ? 'disabled' : ''}">
//                         <a class="page-link" href="#" data-page="${pageNum + 1}" aria-label="Next">
//                             <span aria-hidden="true">&raquo;</span>
//                             <span class="sr-only">Next</span>
//                         </a>
//                     </li>
//                 `;
//             });
//         });
//     }

// });
// function revertCell(cell, oldValue, input) {
//     cell.innerText = oldValue;
//     cell.removeChild(input);
// }