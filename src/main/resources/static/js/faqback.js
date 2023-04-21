







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
        fetch(`/faq/findAll?page=${pageA}${pages}`, {
            method: 'GET',
        }).then((response) => {
            response.json().then(data => {
                const table = document.querySelector('#table');



                const bodyTable = data.map(obj => {

                    const faqno = obj['faqno'];
                    const faqnoa = `<td id="${faqno}" class="faqno">${faqno}</td>`;
                    const faqname = obj['faqname'];
                    const faqnamea = `<td id="${faqname}">${faqname}</td>`;
                    const faqans = obj['faqans'];
                    const faqansa = `<td id="${faqans}">${faqans}</td>`;
                    const faqtag = obj['faqtag'];
                    const faqtaga = `<td id="${faqtag}">${faqtag}</td>`;

                    const fixButton = `<td><button name=${faqno} type="button" class="btn btn-warning m-2 update" onclick="updatebtn(this.name)">修改</button></td>`;
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


            });
        });

    }
});




//const searchInput = document.querySelector('#searchInput');
//const searchBtn = document.querySelector('#searchBtn');
//const resultTable = document.querySelector('#resultTable');
//
//searchBtn.addEventListener('click', () => {
//  const faqtag = searchInput.value;
//  if (!faqtag) return;
//
//  fetch(`/faq/faqSearch?faqtag=${faqtag}`)
//    .then(response => response.json())
//    .then(data => {
//      resultTable.innerHTML = '';
//      data.forEach(search => {
//        const tr = document.createElement('tr');
//        const tdTitle = document.createElement('td');
//        tdTitle.textContent = search.title;
//        tr.appendChild(tdTitle);
//        const tdContent = document.createElement('td');
//        tdContent.textContent = search.content;
//        tr.appendChild(tdContent);
//        resultTable.appendChild(tr);
//      });
//    });
//});
// const searchBtn = document.querySelector('#search-btn');
//      const searchInput = document.querySelector('#search-input');
//      const searchResults = document.querySelector('#search-results');
//
//      searchBtn.addEventListener('click', () => {
//
//        const faqTag = searchInput.value;
//        fetch(`/search?faqtag=${faqTag}`)
//          .then(response => response.json())
//          .then(data => {
//            const results = data.results;
//            let html = '';
//            for (let i = 0; i < results.length; i++) {
//              const result = results[i];
//              html += `<div><h1>${result.title}</h1><p>${result.description}</p></div>`;
//            }
//            searchResults.innerHTML = html;
//          }).catch(error => {
//                  alert(error.message);
//                });
//
//      });


function revertCell(cell, oldValue, input) {
    cell.innerText = oldValue;
    // cell.removeChild(input);
}

function revertCell1(cell1, oldValue1, input1) {
    cell1.innerText = oldValue1;
    // cell1.removeChild(input1);
}

function revertCell2(cell2, oldValue2, input2) {
    cell2.innerText = oldValue2;
    // cell2.removeChild(input2);
}




















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





function updatebtn() {
    const updateBtn = document.querySelectorAll('.update');
    // const updateBtn = document.getElementsByName(`${faqno}`)[0];
    // console.log(event.target);
    // console.log(faqno);

    updateBtn.forEach(button => {
        button.addEventListener('click', () => {
            // event.preventDefault();
            // alert("update...");
            console.log(event.target);
            console.log(faqno);

            // fetch(`/faq/findByNo/${faqno}`), {
            //     method: 'GET'

            // }
            const cell = button.parentElement.previousElementSibling.previousElementSibling;
            const cell1 = button.parentElement.previousElementSibling;
            const cell2 = button.parentElement.previousElementSibling.previousElementSibling.previousElementSibling;
            const oldValue = cell.innerText;
            const oldValue1 = cell1.innerText;
            const oldValue2 = cell2.innerText;
            const input = document.createElement('input');
            const swalWithBootstrapButtons = Swal.mixin({
                customClass: {
                    cancelButton: 'btn btn-danger',
                    confirmButton: 'btn btn-success'
                },
                buttonsStyling: false
            });
            input.setAttribute('type', 'textarea');
            input.setAttribute('value', oldValue);
            cell.innerHTML = '';
            cell.appendChild(input);
            input.focus();
            const input1 = document.createElement('input');
            input1.setAttribute('type', 'text');
            input1.setAttribute('value', oldValue1);
            cell1.innerHTML = '';
            cell1.appendChild(input1);
            input1.focus();
            const input2 = document.createElement('input');
            input2.setAttribute('type', 'text');
            input2.setAttribute('value', oldValue2);
            cell2.innerHTML = '';
            cell2.appendChild(input2);
            input2.focus();
            input.addEventListener('blur', () => {
                const newValue = input.value;

                const newValue1 = input1.value;
                const newValue2 = input2.value;

                if (newValue !== oldValue || newValue1 !== oldValue1 || newValue2 !== oldValue2) {
                    // const swalWithBootstrapButtons = Swal.mixin({
                    //     customClass: {
                    //         cancelButton: 'btn btn-danger',
                    //         confirmButton: 'btn btn-success'
                    //     },
                    //     buttonsStyling: false
                    // });
                    if (
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
                                revertCell(cell, oldValue, input);
                                revertCell1(cell1, oldValue1, input1);
                                revertCell2(cell2, oldValue2, input2);
                            }
                        })
                    ) {
                        // Update the data
                        // const faqname1 = cell.getAttribute('id');
                        // const faqans1 = cell1.getAttribute('id');
                        // const faqtag1 = cell2.getAttribute('id');
                        const row = cell.parentElement.querySelector('td:first-child').getAttribute('id');

                        const faqno = cell.nextElementSibling.getAttribute('id');
                        // Send a PUT request to update the data on the server
                        fetch(`/faq/update/${row}`, {
                            method: 'PUT',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify({
                                faqname: newValue.faqname,
                                faqans: newValue1.faqans,
                                faqtag: newValue2.faqtag,

                                faqno: faqno,
                            }),
                        }).then(() => {
                            // Update the cell in the table
                            cell.innerText = newValue;
                            cell1.innerText = newValue1;
                            cell2.innerText = newValue2;
                            cell.setAttribute('id', newValue);
                            cell1.setAttribute('id', newValue1);
                            cell2.setAttribute('id', newValue2);


                        }).catch(error => console.log(error));
                    } else {
                        revertCell(cell, oldValue, input);
                        revertCell1(cell1, oldValue1, input1);
                        revertCell2(cell2, oldValue2, input2);
                    }
                } else {
                    revertCell(cell, oldValue, input);
                    revertCell1(cell1, oldValue1, input1);
                    revertCell2(cell2, oldValue2, input2);
                }
            });
            // cell.innerHTML = '';
            // cell.appendChild(input);
            // input.focus();
        });
    });
}

//  fix

// const updateBtn = document.querySelector('#btn btn-warning m-2');
// updateBtn = document.querySelector('update');










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


const searchForm = document.querySelector('#search-form');
searchForm.addEventListener('submit', function (event) {
    event.preventDefault(); // 防止表單提交後頁面重整

    const formData = new FormData(searchForm);
    const searchValue = formData.get('search');

    fetch(`faq/find?faqno=${searchValue}`, {
        method: 'GET'
    }).then(response => {
        response.json().then(data => {
            const table = document.querySelector('#table');
            table.querySelector('tbody').innerHTML = '';
            const bodyCells = `
           <td id="${data.faqno}" class="faqno">${data.faqno}</td>
           <td id="${data.faqname}">${data.faqname}</td>

           <td><button type="button" class="btn btn-primary ms-2 btn btn-warning m-2">修改</button></td>
        //    <td><select id="cerSta-${data.cerNo}" class="form-select">

//            </select></td>`
            table.querySelector('tbody').innerHTML += bodyCells;
            updatebtn();
        })
    });
});









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

