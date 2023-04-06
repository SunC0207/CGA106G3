$(document).ready(() => {
    fetch(`/rel/findAll`, {
        method: 'GET',
    }).then((response) => {
        response.json().then(data => {
            const table = document.querySelector('#table');

            const bodyCells = data.map(obj => {
                const religionName = obj['relname'];
                const religionNameCell = `<td id="${religionName}">${religionName}</td>`;
                const editButtonCell = `<td><button type="button" class="btn btn-primary ms-2 update-btn">編輯</button></td>`;
                const row = obj['relno'];
                const rowCells = `<td id="${row}">${row}</td>`
                return `<tr>${rowCells}${religionNameCell}${editButtonCell}</tr>`;
            }).join('');
            table.innerHTML += bodyCells;

            // Add click event listener to all edit buttons
            const updateButtons = document.querySelectorAll('.update-btn');
            updateButtons.forEach(button => {
                button.addEventListener('click', () => {
                    const cell = button.parentElement.previousElementSibling;
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
                                const religionName = cell.getAttribute('id');
                                const row = cell.parentElement.querySelector('td:first-child').getAttribute('id');
                                // Send a PUT request to update the data on the server
                                fetch(`/rel/update/${row}`, {
                                    method: 'PUT',
                                    headers: {
                                        'Content-Type': 'application/json'
                                    },
                                    body: JSON.stringify({
                                        relname: newValue
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
        })
    });
});
function revertCell(cell, oldValue, input) {
    cell.innerText = oldValue;
    cell.removeChild(input);
}



const addButton = document.querySelector('#add');
addButton.addEventListener('click', () => {
    Swal.fire({
        title: '請輸入欲新增的宗教名稱',
        input: 'text',
        inputAttributes: {
            autocapitalize: 'off'
        },
        showCancelButton: true,
        confirmButtonText: '提交',
        showLoaderOnConfirm: true,
        preConfirm: (newReligionName) => {
            if (!newReligionName) {
                Swal.showValidationMessage('請輸入宗教名稱');
            } else {
                return fetch('/rel/add', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        relname: newReligionName
                    })
                }).then(response => {
                    if (!response.ok) {
                        throw new Error('添加失敗');
                    }
                }).catch(error => {
                    Swal.showValidationMessage(`添加失敗: ${error}`);
                })
            }
        },
        allowOutsideClick: () => !Swal.isLoading()
    }).then((result) => {
        if (result.isConfirmed) {
            // Reload the page to show the new data
            location.reload();
        }
    });
});


const searchForm = document.querySelector('#search-form');
searchForm.addEventListener('submit', function (event) {
    event.preventDefault(); // 防止表單提交後頁面重整

    const formData = new FormData(searchForm);
    const searchValue = formData.get('search');

    fetch(`rel/findbyid?relno=${searchValue}`, {
        method: 'GET'
    }).then(response => {
        response.json().then(data => {
            const table = document.querySelector('#table');
            table.innerHTML = '';
            const bodyCells = `<thead>
            <tr>
                <th scope="col">宗教編號</th>
                <th scope="col">宗教名稱</th>
                <th scope="col">修改</th>
            </tr>
            <tr>
                <td id="${data.relno}">${data.relno}</td>
                <td id="${data.relname}">${data.relname}</td>
                <td><button type="button" class="btn btn-primary ms-2 update-btn">編輯</button></td>
            </tr>`
            table.innerHTML += bodyCells;
        })
    });
});







