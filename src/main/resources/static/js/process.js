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
        fetch(`/pro/findAll?page=${pageNum}&size=${pageSize}`, {
            method: 'GET',
        }).then((response) => {
            response.json().then(data => {
                const table = document.querySelector('#table');
                const bodyCells = data.content.map(obj => {
                    const proseq = obj['proseq'];
                    const proseqCell = `<td id="${proseq}" class="proseq">${proseq}</td>`;
                    const prono = obj['prono'];
                    const pronoCell = `<td id="${prono}" class="prono">${prono}</td>`;
                    const proname = obj['proname'];
                    const pronameCell = `<td id="${proname}">${proname}</td>`;
                    const cerno = obj['cerno'];
                    const cernoCell = `<td id="${cerno}">${cerno}</td>`;
                    const prosta = obj['prosta'];
                    const prostaOptions = `
                        <select id="prosta-${prono}" class="form-select">
                            <option value="1" ${prosta === 1 && 'selected'}>上架</option>
                            <option value="2" ${prosta === 2 && 'selected'}>下架</option>
                        </select>`;
                    const prostaCell = `<td>${prostaOptions}</td>`;
                    const editButtonCell = `<td><button type="button" class="btn btn-primary ms-2 update-btn">編輯</button></td>`;
                    return `<tr>${proseqCell}${pronoCell}${pronameCell}${cernoCell}${editButtonCell}${prostaCell}</tr>`;
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
                updatebtn();
            });
        });
    }

});
function revertCell(cell, oldValue, input) {
    cell.innerText = oldValue;
    cell.removeChild(input);
}

function updatebtn() {
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
                        const proname = cell.getAttribute('id');
                        const row = cell.parentElement.querySelector('td:first-child').getAttribute('id');
                        const prosta = cell.parentElement.querySelector('select option:checked').value;
                        const cerno = cell.nextElementSibling.getAttribute('id');
                        const proseq = cell.previousElementSibling.previousElementSibling.getAttribute('id');
                        // Send a PUT request to update the data on the server
                        fetch(`/pro/update/${row}`, {
                            method: 'PUT',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify({
                                proname: newValue,
                                prosta: prosta,
                                cerno: cerno,
                                proseq: proseq
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
}