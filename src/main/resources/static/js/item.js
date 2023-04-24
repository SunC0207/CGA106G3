new Vue({
    el: '#item',
    data() {
        return {
            currentPage: 1,
            pageSize: 1000,
            totalPages: 1,
            searchTerm: '',
            religions: [],
            ceremonies: [],
            items: [],
            processes: [],
            addRelNo: 0,
            addCerNo: 0,
            addProNo: 0,
            additemName: '',
            additemSta: false,
            additemPrice: '',
            newRelName: '',
            newCereName: '',
            newProNo: '',
            newItemName: '',
            newItemSta: '',
            newItemPrice: '',
            newItemNo: '',
            allProcesses: '',
            newOpic: '',
        }
    },
    created() {
        this.showTable();

        fetch('/rel/findAll', {
            method: 'GET',
        }).then(response => response.json())
            .then(data => {
                this.religions = data;
            });

        this.getAllProcesses();
        // 監聽變化，當選擇流程變更時，動態取得宗教 儀式 名稱
        this.$watch('newProNo', (newValue, oldValue) => {
            if (newValue !== 0) {
                fetch(`/pro/findRNameCName?proNo=${newValue}`)
                    .then(response => response.json())
                    .then(data => {
                        this.newRelName = data[0][0]; // 將取得的宗教名稱設定到newRelName中
                        this.newCereName = data[0][1];
                    })
            }
        })
    },
    computed: {
        pageButtons() {
            const startPage = Math.max(1, this.currentPage - 1);
            const endPage = Math.min(this.totalPages, this.currentPage + 1);
            const pages = [];
            for (let i = startPage; i <= endPage; i++) {
                pages.push(i);
            }
            return pages
        },
        regex() {
            return new RegExp(this.searchTerm, "i");
        },
    },
    methods: {
        updateItem() {
            fetch(`/item/update/${this.newItemNo}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    itemNo: this.newItemNo,
                    proNo: this.newProNo,
                    iname: this.newItemName,
                    iprice: this.newItemPrice,
                    ista: this.newItemSta
                })
            }).then((response) => {
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
                    })
                }
            })
        },
        getAllProcesses() {
            fetch('/pro/findAll?page=1&size=100000', {
                method: 'GET',
            }).then((response) => {
                response.json().then((data) => {
                    this.allProcesses = data.content;
                    this.totalPages = data.totalPages;
                });
            });
        },
        goToPage(pageNum) {
            if (pageNum !== this.currentPage) {
                this.showTable(pageNum);
                this.currentPage = pageNum;
            }
        },
        prevPage() {
            if (this.currentPage > 1) {
                this.showTable(this.currentPage - 1);
                this.currentPage -= 1;
            }
        },
        nextPage() {
            if (this.currentPage < this.totalPages) {
                this.showTable(this.currentPage + 1);
                this.currentPage += 1;
            }
        },
        showModal() {
            $('#itemModal').modal('show')
        },
        showOpicModal(item) {
            $('#OpicModal').modal('show')
            this.newOpic = item.upFile
            this.newItemNo = item.itemNo
            this.newItemName = item.iname
        },
        updateOpic() {
            const formData = new FormData();
            formData.append('optNo', this.newItemNo);
            formData.append('picName', this.newItemName);
            formData.append('upFile', this.selectedFile);

            fetch('/optPic/add', {
                method: 'POST',
                body: formData
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
                    })
                }
            })
        },
        onFileSelected(event) {
            this.selectedFile = event.target.files[0];
          },
        showTable() {
            fetch(`/item/itemsAndProName`, {
                method: 'GET',
            }).then((response) => {
                response.json().then((data) => {
                    this.items = data;
                });
            });
        },
        deletePic(item){
            fetch(`/optPic/delete?optNo=${item.itemNo}`, {
                method: 'DELETE',
            }).then(response => {
                if (response.ok) {
                    Swal.fire({
                        icon: 'success',
                        title: '刪除成功',
                        text: '',
                        confirmButtonText: 'OK',
                    }).then(() => {
                        location.reload();
                    })

                } else {
                    Swal.fire({
                        icon: 'error',
                        title: '刪除失敗',
                        showConfirmButton: false,
                        timer: 1500
                    })
                }
            })
        },
        showUpdateModal(item) {
            $('#updateModal').modal('show')
            this.newItemNo = item.itemNo;
            this.newItemName = item.iname;
            this.newItemPrice = item.iprice;
            this.newProNo = item.proNo;
            this.newItemSta = item.ista
        },
        getCeremonies() {
            fetch(`ceremony/ceremoniesByRelno?relNo=${this.addRelNo}`, {
                method: 'GET',
            }).then(response => {
                response.json().then(data => {
                    this.ceremonies = data;
                });
            });
        },
        getProcesses() {
            fetch(`pro/proByCerNo?cerNo=${this.addCerNo}`, {
                method: 'GET',
            }).then(response => {
                response.json().then(data => {
                    this.processes = data;
                });
            });
        },
        additem() {
            fetch('/item/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    proNo: this.addProNo,
                    ista: this.additemSta,
                    iname: this.additemName,
                    iprice: this.additemPrice
                })
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
                    })
                }
            })
        },
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
//         fetch(`/item/findAll?page=${pageNum}&size=${pageSize}`, {
//             method: 'GET',
//         }).then((response) => {
//             response.json().then(data => {
//                 const table = document.querySelector('#table');
//                 const bodyCells = data.content.map(obj => {
//                     const itemNo = obj['itemNo'];
//                     const itemNoCell = `<td id="${itemNo}" class="itemNo">${itemNo}</td>`;
//                     const iname = obj['iname'];
//                     const inameCell = `<td id="${iname}">${iname}</td>`;
//                     const iprice = obj['iprice'];
//                     const ipriceCell = `<td id="${iprice}">$${iprice}</td>`;
//                     const proNo = obj['proNo'];
//                     const proNoCell = `<td id="${proNo}">${proNo}</td>`;
//                     const ista = obj['ista'];
//                     const istaOptions = `
//                         <select id="ista-${itemNo}" class="form-select">
//                             <option value="1" ${ista === 1 && 'selected'}>上架</option>
//                             <option value="2" ${ista === 2 && 'selected'}>下架</option>
//                         </select>`;
//                     const istaCell = `<td>${istaOptions}</td>`;
//                     const editButtonCell = `<td><button type="button" class="btn btn-primary ms-2 update-btn">編輯</button></td>`;
//                     return `<tr>${itemNoCell}${inameCell}${ipriceCell}${proNoCell}${editButtonCell}${istaCell}</tr>`;
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
//                 updatebtn();
//             });
//         });
//     }

// });
// function revertCell(cell, oldValue, input) {
//     cell.innerText = oldValue;
//     cell.removeChild(input);
// }

// function updatebtn() {
//     const updateButtons = document.querySelectorAll('.update-btn');
//     updateButtons.forEach(button => {
//         button.addEventListener('click', () => {
//             const cell = button.parentElement.previousElementSibling.previousElementSibling.previousElementSibling;
//             const oldValue = cell.innerText;
//             const input = document.createElement('input');
//             const swalWithBootstrapButtons = Swal.mixin({
//                 customClass: {
//                     cancelButton: 'btn btn-danger',
//                     confirmButton: 'btn btn-success'
//                 },
//                 buttonsStyling: false
//             });
//             input.setAttribute('type', 'text');
//             input.setAttribute('value', oldValue);
//             input.addEventListener('blur', () => {
//                 const newValue = input.value;
//                 if (newValue !== oldValue) {
//                     if (
//                         swalWithBootstrapButtons.fire({
//                             title: '確定修改?',
//                             icon: 'warning',
//                             showCancelButton: true,
//                             confirmButtonText: '確認修改',
//                             cancelButtonText: '取消',
//                             reverseButtons: true
//                         }).then((result) => {
//                             if (result.isConfirmed) {
//                                 swalWithBootstrapButtons.fire(
//                                     '成功!',
//                                     '',
//                                     'success'
//                                 )
//                             } else if (
//                                 result.dismiss === Swal.DismissReason.cancel
//                             ) {
//                                 swalWithBootstrapButtons.fire(
//                                     '取消',
//                                     '',
//                                     'error'
//                                 )
//                                 revertCell(cell, oldValue, input);
//                             }
//                         })
//                     ) {
//                         // Update the data
//                         const iname = cell.getAttribute('id');
//                         const row = cell.parentElement.querySelector('td:first-child').getAttribute('id');
//                         const ista = cell.parentElement.querySelector('select option:checked').value;
//                         const iprice = cell.nextElementSibling.getAttribute('id');
//                         const proNo = cell.nextElementSibling.nextElementSibling.getAttribute('id');
//                         // Send a PUT request to update the data on the server
//                         fetch(`/item/update/${row}`, {
//                             method: 'PUT',
//                             headers: {
//                                 'Content-Type': 'application/json'
//                             },
//                             body: JSON.stringify({
//                                 iname: newValue,
//                                 ista: ista,
//                                 proNo: proNo,
//                                 iprice: iprice
//                             })
//                         }).then(() => {
//                             // Update the cell in the table
//                             cell.innerText = newValue;
//                             cell.setAttribute('id', newValue);
//                         }).catch(error => console.log(error));
//                     } else {
//                         revertCell(cell, oldValue, input);
//                     }
//                 } else {
//                     revertCell(cell, oldValue, input);
//                 }
//             });
//             cell.innerHTML = '';
//             cell.appendChild(input);
//             input.focus();
//         });
//     });
// }
