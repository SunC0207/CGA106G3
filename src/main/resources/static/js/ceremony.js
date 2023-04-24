new Vue({
    el: '#ceremony',
    data() {
        return {
            currentPage: 1,
            pageSize: 100,
            totalPages: 1,
            ceremonies: [],
            newCereName: '',
            newCereStr: 2,
            newCereNo: '',
            newRelNo: 0,
            religions: [],
            searchTerm: '',
            addRelNo: 0,
            addCereName: '',
            addCereStr: 2
        }
    },
    created() {
        // 初始化時顯示第一頁
        this.showTable(this.currentPage);
        fetch('/rel/findAll', {
            method: 'GET',
        }).then(response => response.json())
            .then(data => {
                this.religions = data;
            });
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
        showTable(pageNum) {
            fetch(`/ceremony/findAll?page=${pageNum}&size=${this.pageSize}`, {
                method: 'GET',
            }).then((response) => {
                response.json().then((data) => {
                    this.ceremonies = data.content;
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
        showUpdateModal(cere) {
            $('#updateModal').modal('show');
            this.newCereName = cere.cerName;
            this.newCereStr = cere.cerSta;
            this.newCereNo = cere.cerNo;
            this.newRelNo = cere.relNo;
        },
        showModal() {
            $('#ceremonyModal').modal('show');
        },
        updateCere() {
            fetch(`/ceremony/update/${this.newCereNo}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    cerNo: this.newCereNo,
                    cerName: this.newCereName,
                    cerSta: this.newCereStr,
                    relNo: this.newRelNo,
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
        addCeremony() {
            fetch('/ceremony/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    cerName: this.addCereName,
                    cerSta: this.addCereStr,
                    relNo: this.addRelNo,
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






// $(document).on('change', '.form-select', function () {
//     const cerNoText = $(this).closest('tr').find('.cerNo').text();
//     const cerSta = $(this).val();
//     $.ajax({
//         url: '/ceremony/updateCerSta',
//         method: 'POST',
//         data: {
//             cerNo: cerNoText,
//             cerSta: cerSta
//         },
//         datatype: 'json',
//         success: function (response) {
//             console.log(response);
//             Swal.fire('狀態修改成功');
//         },
//         error: function (error) {
//             console.log(error);
//             Swal.fire('狀態修改失敗');
//         }
//     });
// });

// function updatebtn() {
//     const updateButtons = document.querySelectorAll('.update-btn');
//     updateButtons.forEach(button => {
//         button.addEventListener('click', () => {
//             const cell = button.parentElement.previousElementSibling.previousElementSibling;
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
//                         const cerName = cell.getAttribute('id');
//                         const row = cell.parentElement.querySelector('td:first-child').getAttribute('id');
//                         const cerSta = cell.parentElement.querySelector('select option:checked').value;
//                         const relNo = cell.nextElementSibling.getAttribute('id');
//                         // Send a PUT request to update the data on the server
//                         fetch(`/ceremony/update/${row}`, {
//                             method: 'PUT',
//                             headers: {
//                                 'Content-Type': 'application/json'
//                             },
//                             body: JSON.stringify({
//                                 cerName: newValue,
//                                 cerSta: cerSta,
//                                 relNo: relNo
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

// const addButton = document.querySelector('#add');
// addButton.addEventListener('click', () => {
//     (async () => {

//         const { value: formValues } = await Swal.fire({
//             title: '新增儀式',
//             html:
//                 '儀式名稱：<input id="swal-input1" class="swal2-input">' +
//                 '宗教：<select id="swal-input2" class="swal2-input"><option value="17001">中式</option><option value="17002">西式</option><option value="17003">佛教</option></select>',
//             focusConfirm: false,
//             preConfirm: () => {
//                 return [
//                     document.querySelector('#swal-input1').value,
//                     document.querySelector('#swal-input2').options[document.getElementById('swal-input2').selectedIndex].value
//                 ]
//             }
//         })

//         if (formValues) {
//             const cere = {
//                 cerName: formValues[0],
//                 relNo: formValues[1],
//                 cerSta: 2
//             };

//             fetch('/ceremony/add', {
//                 method: 'POST',
//                 headers: {
//                     'Content-Type': 'application/json'
//                 },
//                 body: JSON.stringify(cere)
//             })
//                 .then(response => {
//                     if (response.ok) {
//                         return response.json();
//                     } else {
//                         throw new Error(response.statusText);
//                     }
//                 })
//                 .then(data => {
//                     Swal.fire({
//                         icon: 'success',
//                         title: '新增成功'
//                     });
//                 })
//                 .catch(error => {
//                     Swal.fire({
//                         icon: 'error',
//                         title: '新增失敗',
//                         text: error.message
//                     });
//                 });
//         }

//     })()
// });

// const searchForm = document.querySelector('#search-form');
// searchForm.addEventListener('submit', function (event) {
//     event.preventDefault(); // 防止表單提交後頁面重整

//     const formData = new FormData(searchForm);
//     const searchValue = formData.get('search');

//     fetch(`ceremony/find?cerNo=${searchValue}`, {
//         method: 'GET'
//     }).then(response => {
//         response.json().then(data => {
//             const table = document.querySelector('#table');
//             table.querySelector('tbody').innerHTML = '';
//             const bodyCells = `
//             <td id="${data.cerNo}" class="cerNo">${data.cerNo}</td>
//             <td id="${data.cerName}">${data.cerName}</td>
//             <td id="${data.relNo}">${data.relNo}</td>
//             <td><button type="button" class="btn btn-primary ms-2 update-btn">編輯</button></td>
//             <td><select id="cerSta-${data.cerNo}" class="form-select">
//                 <option value="1" ${data.cerSta === 1 && 'selected'}>上架</option>
//                 <option value="2" ${data.cerSta === 2 && 'selected'}>下架</option>
//             </select></td>`
//             table.querySelector('tbody').innerHTML += bodyCells;
//             updatebtn();
//         })
//     });
// });