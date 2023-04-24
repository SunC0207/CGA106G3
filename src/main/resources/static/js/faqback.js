







$(document).ready(() => {
    const pages = 10;
    let page = 1;

    show(page);

    $(document).on('click', '.page-link', function () {
        const pageA = parseInt($(this).data('page1'));
        if (pageA !== page) {
            show(pageA);
        }
    });






    function show(pageA) {
        page = pageA;
        fetch(`/faq/findAll?page=${pageA}&size=${pages}`, {
            method: 'GET',
        }).then((response) => {
            response.json().then(data => {
                const table = document.querySelector('#table');
                table.style.setProperty("background-color", "black", "important");


                const bodyTable = data.map(obj => {

                    const faqno = obj['faqno'];
                    const faqnoa = `<td id="${faqno}" class="faqno">${faqno}</td>`;
                    const faqname = obj['faqname'];
                    const faqnamea = `<td id="${faqname}">${faqname}</td>`;
                    const faqans = obj['faqans'];
                    const faqansa = `<td id="${faqans}">${faqans}</td>`;
                    const faqtag = obj['faqtag'];
                    const faqtaga = `<td id="${faqtag}">${faqtag}</td>`;

                    const fixButton = `<td><button name=${faqno} type="button" class="btn btn-warning m-2 update" >修改</button></td>`;
                    const delButton = `<td><button name=${faqno} type="button" class="btn btn-danger m-2" onclick="deleteData(this.name)">刪除</button></td>`;
                    return `<tr>${faqnoa}${faqnamea}${faqansa}${faqtaga}${fixButton}${delButton}</tr>`;
                }).join('');
                table.querySelector('tbody').innerHTML = bodyTable;


                const pagination = document.querySelector('.pagination');
                const totalPage = data.totalPages;
                const startPage = Math.max(1, pageA - 1);
                const endPage = Math.min(totalPage, pageA + 1);
                let pageButtons = '';
                for (let i = startPage; i <= endPage; i++) {

                    pageButtons += `<li class="page-item ${i === pageA ? 'active' : ''}"><a class="page-link" href="#" data-page="${i}">${i}</a></li>`;
                }
                pagination.innerHTML = `<li class="page-item ${pageA === 1 ? 'disabled' : ''}">
<a class="page-link" href="#" data-page="${pageA - 1}" aria-label="Previous">
<span aria-hidden="true">&laquo;</span>
<span class="sr-only">Previous</span>
</a>
</li>
${pageButtons}
<li class="page-item ${pageA === totalPage ? 'disabled' : ''}">
<a class="page-link" href="#" data-page="${pageA + 1}" aria-label="Next">
<span aria-hidden="true">&raquo;</span>
<span class="sr-only">Next</span>
</a>
</li>`;
                updatebtn();


                $('#table').DataTable({
                    pageLength: 5,
                    lengthMenu: [5, 10, 15, 20],
                    language: {
                        emptyTable: "無資料",
                        info: "顯示 START 至 END 筆資料，共 TOTAL 筆",
                        lengthMenu: "顯示 MENU 筆資料",
                        paginate: {
                            previous: '<i class="fa fa-chevron-left"></i>',
                            next: '<i class="fa fa-chevron-right"></i>'
                        },
                    },
                });
                let label = document.querySelector('#table_length label');
                label.style.color = '#000';


                let size1 = document.querySelectorAll('.sorting');
                size1.style.width = '500px';


            });
        });

    }
});




// const searchInput = document.querySelector('#searchInput');
// const searchBtn = document.querySelector('#searchBtn');
// const resultTable = document.querySelector('#resultTable');

// searchBtn.addEventListener('click', () => {
//     const faqtag = searchInput.value;
//     if (!faqtag) return;

//     fetch(`/faq/faqSearch?faqtag=${faqtag}`)
//         .then(response => response.json())
//         .then(data => {
//             resultTable.innerHTML = '';
//             data.forEach(search => {
//                 const tr = document.createElement('tr');
//                 const tdTitle = document.createElement('td');
//                 tdTitle.textContent = search.title;
//                 tr.appendChild(tdTitle);
//                 const tdContent = document.createElement('td');
//                 tdContent.textContent = search.content;
//                 tr.appendChild(tdContent);
//                 resultTable.appendChild(tr);
//             });
//         });
// });
























// function updatebtn(faqno) {

// 获取所有编辑按钮
// const editBtns = document.querySelectorAll('.update');

// // 遍历每个编辑按钮
// editBtns.forEach(editBtn => {
//     // 添加点击事件监听器
//     editBtn.addEventListener('click', () => {
//         // 获取该FAQ的主键
//         const faqNo = editBtn.dataset.id;

//         // 从数据库中获取该FAQ的信息
//         const faq = database.faqs.find(faq => faq.faqno === faqNo);

//         // 显示一个表单，允许用户编辑该FAQ的信息
//         showEditForm(faq);
//     });
// });

// // 显示编辑表单的函数
// function showEditForm(faq) {
//     // 创建一个表单元素
//     const form = document.createElement('form');

//     // 创建输入框和标签元素，用于编辑FAQ信息
//     const nameInput = createInput('text', 'faqname', faq.faqname);
//     const ansInput = createInput('text', 'faqans', faq.faqans);
//     const tagInput = createInput('text', 'faqtag', faq.faqtag);

//     // 创建提交按钮元素
//     const submitBtn = document.createElement('button');
//     submitBtn.type = 'submit';
//     submitBtn.textContent = 'Save';

//     // 将表单元素和表单控件添加到文档中
//     form.appendChild(nameInput.label);
//     form.appendChild(nameInput.input);
//     form.appendChild(ansInput.label);
//     form.appendChild(ansInput.input);
//     form.appendChild(tagInput.label);
//     form.appendChild(tagInput.input);
//     form.appendChild(submitBtn);
//     document.body.appendChild(form);

//     // 添加表单提交事件监听器
//     form.addEventListener('submit', (event) => {
//         event.preventDefault();

//         // 更新数据库中该FAQ的信息
//         database.faqs.find(faq => faq.faqno === faq.faqno).faqname = nameInput.input.value;
//         database.faqs.find(faq => faq.faqno === faq.faqno).faqans = ansInput.input.value;
//         database.faqs.find(faq => faq.faqno === faq.faqno).faqtag = tagInput.input.value;

//         // 隐藏表单元素
//         form.style.display = 'none';

//         // 更新表格中该FAQ的信息
//         const row = document.querySelector(`tr[data-id="${faq.faqno}"]`);
//         row.querySelector('td[data-field="faqname"]').textContent = nameInput.input.value;
//         row.querySelector('td[data-field="faqans"]').textContent = ansInput.input.value;
//         row.querySelector('td[data-field="faqtag"]').textContent = tagInput.input.value;

//     });
// }

// 创建输入框和标签元素的函数
// function createInput(type, name, value) {
//     const label = document.createElement('label');
//     label.textContent = name;

//     const input = document.createElement('input');
//     input.type = type;
//     input.name = name;
//     input.value = value;

//     return { label, input };
// }
// };



let cell;
let row;

function updatebtn() {
    const updateBtn = document.querySelectorAll('.update');
    // const updateBtn = document.getElementsByName(`${faqno}`)[0];
    // console.log(event.target);
    // console.log(faqno);

    updateBtn.forEach(button => {
        button.addEventListener('click', () => {
            $('#updateModal').modal('show');
            cell = button.parentElement;
            row = cell.parentElement.querySelector('td:first-child').getAttribute('id');



        })
    })
}

function updateFaq() {
    // const newname = $('#faqname').value;
    // const newans = $('#faqans').value;
    // const newtag = $('#faqtag').value;
    const newname = document.getElementById('faqname').value;
    const newans = document.getElementById('faqans').value;
    const newtag = document.getElementById('faqtag').value;

    fetch(`/faq/update/${row}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            faqname: newname,
            faqans: newans,
            faqtag: newtag,
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
}





swalWithBootstrapButtons.fire({
    title: '確定修改?',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: '確認修改',
    cancelButtonText: '取消',
    reverseButtons: true
}).then((result) => {
    if (result.isConfirmed) {
        swalWithBootstrapButtons.fire(
            '成功!',
            '',
            'success'
        )
    } else if (
        result.dismiss === Swal.DismissReason.cancel
    ) {
        swalWithBootstrapButtons.fire(
            '取消',
            '',
            'error'
        )
    }
})









const addBtn = document.querySelector('#add');
addBtn.addEventListener('click', () => {



    // (async () => {

    //     const { value: formValues } = await Swal.fire({
    //         title: '新增',
    //         html:
    //             '問題名稱: <input type="text" class="form-control" id="floatingInput"placeholder="請輸入問題">' +
    //             '問題答案: <textarea class="form-control" placeholder="請輸入問題答案"id="floatingTextarea" style="height: 150px;"></textarea>' +
    //             '標籤: <input type="text" class="form-control" id="floatingPassword"placeholder="請輸入標籤"><label for="floatingPassword">標籤</label>',








    //         focusConfirm: false,
    //         preConfirm: () => {
    //             return [
    // document.querySelector('#floatingInput').value,
    //     document.querySelector('#floatingTextarea').value,
    //     document.querySelector('#floatingPassword').value
    //                 // document.querySelector('#swal-input1').value,
    //                 // document.querySelector('#swal-input2').options[document.getElementById('swal-input2').selectedIndex].value

    //             ];

    //         }


    //     })


    // if (formValues) {
    const faqTest = {
        faqname: document.querySelector('#floatingInput').value,
        faqans: document.querySelector('#floatingTextarea').value,
        faqtag: document.querySelector('#floatingPassword').value

    };

    fetch('/faq/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'

        },
        body: JSON.stringify(faqTest)

    })
        .then(response => {
            if (response.ok) {
                return response.json();

            } else {
                throw new Error(response.statusText);
            }

        })
        .then(data => {
            Swal.fire({
                icon: 'success',
                title: '新增成功'

            });

        })
        .catch(error => {
            Swal.fire({
                icon: 'error',
                title: '新增失敗',
                text: error.message

            });

        });
    // }

    // })
});


// const searchForm = document.querySelector('#search-form');
// searchForm.addEventListener('submit', function (event) {
//     event.preventDefault(); // 防止表單提交後頁面重整

//     const formData = new FormData(searchForm);
//     const searchValue = formData.get('search');












//     fetch(`faq/find?faqno=${searchValue}`, {
//         method: 'GET'
//     }).then(response => {
//         response.json().then(data => {
//             const table = document.querySelector('#table');
//             table.querySelector('tbody').innerHTML = '';
//             const bodyCells = `
//            <td id="${data.faqno}" class="faqno">${data.faqno}</td>
//            <td id="${data.faqname}">${data.faqname}</td>

//            <td><button type="button" class="btn btn-primary ms-2 btn btn-warning m-2">修改</button></td>
//         //    <td><select id="cerSta-${data.cerNo}" class="form-select">

// //            </select></td>`
//             table.querySelector('tbody').innerHTML += bodyCells;
//             updatebtn();
//         })
//     });
// });



// const searchForm = document.querySelector('#search-form');
// const searchInput = document.querySelector('#search-input');

// const tbody = document.querySelector('#tbody');

// searchForm.addEventListener('submit', (event) => {
//     event.preventDefault();
//     const searchTerm = searchInput.value;

//     fetch(`/search?faqtag=${searchTerm}`)
//         .then(response => response.json())
//         .then(data => {

//             tbody.innerHTML = '';

//             for (let faq of data) {
//                 const tr = document.createElement('tr');
//                 tr.innerHTML = `
//           <th scope="row">${faq.id}</th>
//           <td>${faq.name}</td>
//           <td>${faq.answer}</td>
//           <td>${faq.faqtag}</td>

//         `;
//                 tbody.appendChild(tr);
//             }

//         }).catch(error => {
//             console.error(error);
//         });
// });





















function deleteData(id) {
    let row = id;
    fetch(`/faq/delete/${row}`, {
        method: 'POST'
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error(response.statusText);
            }
        })
        .then(data => {
            console.log(data);
            // 刪除成功後，重新載入資料
            // loadData();

            Swal.fire({
                title: '確定刪除嗎?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: '確定',
                cancelButtonText: '取消'
            }).then((result) => {
                if (result.isConfirmed) {
                    location.reload(); // 重新加载页面
                }
            });

            // Swal.fire({
            //     icon: 'success',
            //     title: '刪除成功',
            // });

        })
        .catch(error => {
            Swal.fire({
                icon: 'error',
                title: '刪除失敗',
                text: error.message
            });
        });
    //     location.reload();
}

// 選取刪除按鈕
const deleteBtn = document.querySelectorAll('.btn btn danger m-2');

//deleteBtn.forEach(button => {
//    button.addEventListener('click', () => {
//        console.log(6666);
        // 取得此按鈕所在列的 ID
//        const id = button.parentElement.previousElementSibling.previousElementSibling.previousElementSibling.previousElementSibling.getAttribute('id');
        // 提示是否確定刪除
//        Swal.fire({
//            title: '確定刪除嗎?',
//            icon: 'warning',
//            showCancelButton: true,
//            confirmButtonText: '確定',
//            cancelButtonText: '取消'
//        })
//            .then((result) => {
//                if (result.isConfirmed) {
//                    // 如果確定刪除，呼叫刪除函式
//                    deleteData(id);
//                }
//            });
//    });
//});


// const addBtn = document.getElementById("add");
// const list = document.getElementById("list");

