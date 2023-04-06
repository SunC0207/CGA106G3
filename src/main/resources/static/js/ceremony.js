$(document).ready(() => {
    const pageSize = 5;
    let currentPage = 1;

    // 初始化時顯示第一頁
    showTable(currentPage);

    // 監聽分頁按鈕
    $(document).on('click', '.page-link', function () {
        const pageNum = parseInt($(this).data('page'));
        if (pageNum !== currentPage) {
            showTable(pageNum);
        }
    });

    // 顯示表格
    function showTable(pageNum) {
        currentPage = pageNum;
        fetch(`/cere/findAll?page=${pageNum}&size=${pageSize}`, {
            method: 'GET',
        }).then((response) => {
            response.json().then(data => {
                const table = document.querySelector('#table');
                const bodyCells = data.content.map(obj => {
                    const cerno = obj['cerno'];
                    const cernoCell = `<td id="${cerno}" class="cerno">${cerno}</td>`;
                    const cername = obj['cername'];
                    const cernameCell = `<td id="${cername}">${cername}</td>`;
                    const relno = obj['relno'];
                    const relnoCell = `<td id="${relno}">${relno}</td>`;
                    const cersta = obj['cersta'];
                    const cerstaOptions = `
                        <select id="cersta-${cerno}" class="form-select">
                            <option value="1" ${cersta === 1 && 'selected'}>上架</option>
                            <option value="2" ${cersta === 2 && 'selected'}>下架</option>
                        </select>`;
                    const cerstaCell = `<td>${cerstaOptions}</td>`;
                    const editButtonCell = `<td><button type="button" class="btn btn-primary ms-2 update-btn">編輯</button></td>`;
                    return `<tr>${cernoCell}${cernameCell}${relnoCell}${editButtonCell}${cerstaCell}</tr>`;
                }).join('');
                table.querySelector('tbody').innerHTML = bodyCells;

                // 更新分頁按鈕
                const pagination = document.querySelector('.pagination');
                const totalPage = data.totalPages;
                const startPage = Math.max(1, pageNum - 1);
                const endPage = Math.min(totalPage, pageNum + 1);
                let pageButtons = '';
                for (let i = startPage; i <= endPage; i++) {
                    pageButtons += `<li class="page-item ${i === pageNum ? 'active' : ''}"><a class="page-link" href="#" data-page="${i}">${i}</a></li>`;
                }
                pagination.innerHTML = `
                    <li class="page-item ${pageNum === 1 ? 'disabled' : ''}">
                        <a class="page-link" href="#" data-page="${pageNum - 1}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </li>
                    ${pageButtons}
                    <li class="page-item ${pageNum === totalPage ? 'disabled' : ''}">
                        <a class="page-link" href="#" data-page="${pageNum + 1}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </li>
                `;
                const updateButtons = document.querySelectorAll('.update-btn');
                updateButtons.forEach(button => {
                    button.addEventListener('click', () => {
                        const cell = button.parentElement.previousElementSibling.previousElementSibling;
                        const oldValue = cell.innerText;
                        const input = document.createElement('input');
                        const swalWithBootstrapButtons = Swal.mixin({
                            customClass: {
                                cancelButton: 'btn btn-danger',
                                confirmButton: 'btn btn-success'
                            },
                            buttonsStyling: false
                        });
                        input.setAttribute('type', 'text');
                        input.setAttribute('value', oldValue);
                        input.addEventListener('blur', () => {
                            const newValue = input.value;
                            if (newValue !== oldValue) {
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
                                        }
                                    })
                                ) {
                                    // Update the data
                                    const cername = cell.getAttribute('id');
                                    const row = cell.parentElement.querySelector('td:first-child').getAttribute('id');
                                    const cersta = cell.parentElement.querySelector('select option:checked').value;
                                    const relno = cell.nextElementSibling.getAttribute('id');
                                    // Send a PUT request to update the data on the server
                                    fetch(`/cere/update/${row}`, {
                                        method: 'PUT',
                                        headers: {
                                            'Content-Type': 'application/json'
                                        },
                                        body: JSON.stringify({
                                            cername: newValue,
                                            cersta: cersta,
                                            relno: relno
                                        })
                                    }).then(() => {
                                        // Update the cell in the table
                                        cell.innerText = newValue;
                                        cell.setAttribute('id', newValue);
                                    }).catch(error => console.log(error));
                                } else {
                                    revertCell(cell, oldValue, input);
                                }
                            } else {
                                revertCell(cell, oldValue, input);
                            }
                        });
                        cell.innerHTML = '';
                        cell.appendChild(input);
                        input.focus();
                    });
                });
            });
        });
    }

});
function revertCell(cell, oldValue, input) {
    cell.innerText = oldValue;
    cell.removeChild(input);
}


$(document).on('change', '.form-select', function () {
    const cernoText = $(this).closest('tr').find('.cerno').text();
    const cersta = $(this).val();
    $.ajax({
        url: '/cere/updateCersta',
        method: 'POST',
        data: {
            cerno: cernoText,
            cersta: cersta
        },
        datatype: 'json',
        success: function (response) {
            console.log(response);
            Swal.fire('狀態修改成功');
        },
        error: function (error) {
            console.log(error);
            Swal.fire('狀態修改失敗');
        }
    });
});

const addButton = document.querySelector('#add');
addButton.addEventListener('click', () => {
    (async () => {

        const { value: formValues } = await Swal.fire({
            title: '新增儀式',
            html:
                '儀式名稱：<input id="swal-input1" class="swal2-input">' +
                '宗教：<select id="swal-input2" class="swal2-input"><option value="17001">中式</option><option value="17002">西式</option><option value="17003">佛教</option></select>',
            focusConfirm: false,
            preConfirm: () => {
                return [
                    document.querySelector('#swal-input1').value,
                    document.querySelector('#swal-input2').options[document.getElementById('swal-input2').selectedIndex].value
                ]
            }
        })

        if (formValues) {
            const cere = {
                cername: formValues[0],
                relno: formValues[1],
                cersta: 2
            };

            fetch('/cere/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(cere)
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
        }

    })()
});

const searchForm = document.querySelector('#search-form');
searchForm.addEventListener('submit', function (event) {
    event.preventDefault(); // 防止表單提交後頁面重整

    const formData = new FormData(searchForm);
    const searchValue = formData.get('search');

    fetch(`cere/find?cerno=${searchValue}`, {
        method: 'GET'
    }).then(response => {
        response.json().then(data => {
            const table = document.querySelector('#table');
            table.querySelector('tbody').innerHTML = '';
            const bodyCells = `
            <td id="${data.cerno}" class="cerno">${data.cerno}</td>
            <td id="${data.cername}">${data.cername}</td>
            <td id="${data.relno}">${data.relno}</td>
            <td><button type="button" class="btn btn-primary ms-2 update-btn">編輯</button></td>
            <td><select id="cersta-${data.cerno}" class="form-select">
                <option value="1" ${data.cersta === 1 && 'selected'}>上架</option>
                <option value="2" ${data.cersta === 2 && 'selected'}>下架</option>
            </select></td>`
            table.querySelector('tbody').innerHTML += bodyCells;
        })
    });
});