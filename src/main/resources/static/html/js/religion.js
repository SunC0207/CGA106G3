$(document).ready(() => {
    fetch(`/rel/findAll`, {
        method: 'GET',
    }).then((response) => {
        response.json().then(data => {
            // Create a table element
            const table = document.querySelector('#table');

            // Create the table body rows with an edit button and religion name input field
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
                    const input = document.createElement('input');
                    input.setAttribute('type', 'text');
                    input.setAttribute('value', cell.innerText);
                    input.addEventListener('blur', () => {
                        const newValue = input.value;
                        const oldValue = cell.innerText;
                        if (newValue !== oldValue) {
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
                        }
                        cell.removeChild(input);
                    });
                    cell.innerHTML = '';
                    cell.appendChild(input);
                    input.focus();
                });
            });
        })
    });
});

const addButton = document.querySelector('#add');
addButton.addEventListener('click', () => {
    const newReligionName = prompt('請輸入欲新增的宗教名稱');
    if (newReligionName !== null) {
        fetch('/rel/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                relname: newReligionName
            })
        }).then(response => {
            if (response.ok) {
                // Reload the page to show the new data
                location.reload();
            } else {
                throw new Error('添加宗教失败');
            }
        }).catch(error => console.log(error));
    }
});






