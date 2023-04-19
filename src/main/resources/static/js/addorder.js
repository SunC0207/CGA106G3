
new Vue({
    el: '#template',
    data() {
        return {
            selectedLoc: 0,
            selectedCereDate: null,
            selectedReligion: 0,
            selectedReligionName: '',
            selectedCeremony: 0,
            selectedProcess: 0,
            selectedCereName: null,
            selectedProName: null,
            locations: [],
            religions: [],
            ceremonies: [],
            processes: [],
            selectedPrices: [],
            bodyCells: '',
            dname: '',
            dBirth: '',
            dDate: '',
            membno: '',
            ename: '',
            iPrice: '',
            selectedEmp: 0,
            emps: [],
            poDate: new Date().toISOString().slice(0, 10),
            poType: 0,
        };
    },
    // 自動執行
    created() {
        // DOM 載入完成後執行裡面動作
        this.$nextTick(() => {
            const detailBody = this.$refs.detailBody;
            // 監聽detailBody的子元素 若有變化就執行totalIprice()
            const observer = new MutationObserver(this.totalIprice);
            observer.observe(detailBody, { childList: true });
        });
    },
    mounted() {
        this.getEmp();
        this.getReligions();
        this.tbody = this.$refs.tbody;

        // 重整清localstorage
        window.onbeforeunload = () => {
            window.localStorage.clear();
        }
    },
    methods: {
        // setStorageLoc(){
        //     const key = this.selectedCeremony + '-Location'
        //     localStorage.setItem(key, this.selectedLoc)
        // },
        getcereLoc() {
            fetch('/Loc/getAllLoc', {
                method: 'GET'
            }).then(response => {
                response.json().then(data => {
                    this.locations = data;
                })
            })
        },
        // getceretime(){
        //     const date = new Date(this.selectedCereDate);
        //     const year = date.getFullYear();
        //     const month = String(date.getMonth() + 1).padStart(2, '0');
        //     const day = String(date.getDate()).padStart(2, '0');
        //     const hour = String(date.getHours()).padStart(2, '0');
        //     const minute = String(date.getMinutes()).padStart(2, '0');
        //     const second = String(date.getSeconds()).padStart(2, '0');
        //     const formattedTime = `${year}-${month}-${day} ${hour}:${minute}:${second}`;
        //     const key = this.selectedCeremony + '-DateTime'
        //     this.selectedCereDate = this.selectedCereDate.toString().replace('T', ' ');
        //     localStorage.setItem(key, this.selectedCereDate)
        // },
        getEmp() {
            fetch(`/empManage/getAll`, {
                method: 'GET'
            }).then(response => {
                response.json().then(data => {
                    this.emps = data;
                })
            })
        },
        clear() {
            this.dname = '';
            this.dBirth = '';
            this.dDate = '';
            this.poDate = '';
            this.poType = '';
            this.membno = '';
            this.selectedEmp.empno = '';
        },
        addlocalStorage() {
            localStorage.setItem("dname", this.dname);
            localStorage.setItem("dBirth", this.dBirth);
            localStorage.setItem("dDate", this.dDate);
            localStorage.setItem("poDate", this.poDate);
            localStorage.setItem("poType", this.poType);
            localStorage.setItem("membno", this.membno);
            localStorage.setItem("empno", this.selectedEmp.empno);
            localStorage.setItem("ename", this.selectedEmp.ename);

            this.clear();
        },
        modalGetItem() {
            this.dname = localStorage.getItem("dname");
            this.dBirth = localStorage.getItem("dBirth");
            this.dDate = localStorage.getItem("dDate");
            this.poDate = localStorage.getItem("poDate");
            this.poType = localStorage.getItem("poType");
            this.membno = localStorage.getItem("membno");
            this.ename = this.selectedEmp.ename;
        },
        getReligions() {
            fetch(`rel/findAll`, {
                method: 'GET'
            }).then(response => {
                response.json().then(data => {
                    this.religions = data;
                });
            });
        },
        getCeremonies() {
            if (this.selectedReligion) {
                fetch(`ceremony/ceremoniesByRelno?relNo=${this.selectedReligion}`, {
                    method: 'GET',
                }).then(response => {
                    response.json().then(data => {
                        this.ceremonies = data;
                    });
                });
            }
            this.selectedReligionName = this.religions.find(r => r.relNo === this.selectedReligion)?.relName || '';
        },
        getProcesses() {
            this.selectedCereName = this.ceremonies.find(c => c.cerNo === this.selectedCeremony)?.cerName || '';
            fetch(`pro/proByCerNo?cerNo=${this.selectedCeremony}`, {
                method: 'GET',
            }).then(response => {
                response.json().then(data => {
                    this.processes = data;
                });
            });
            this.selectedCereDate = '';
            this.selectedLoc = 0;
        },
        fetchSelectedItem() {
            this.selectedProName = this.processes.find(p => p.proNo === this.selectedProcess)?.proName || '';
            if (this.selectedProcess) {
                fetch(`/item/itemJoinRelCerePro?proNo=${this.selectedProcess}`, {
                    method: 'GET',
                }).then((response) => {
                    response.json().then(data => {
                        if (Array.isArray(data)) {
                            this.bodyCells = data.map(obj => {
                                const cerNo = obj['cerNo'];
                                const cerName = obj['cerName'];
                                const cereCell = `<td id="${cerNo}">${cerName}</td>`;
                                const itemNo = obj['itemNo'];
                                const iname = obj['iname'];
                                const inameCell = `<td id="${itemNo}">${iname}</td>`;
                                const iprice = obj['iprice'];
                                const ipriceCell = `<td value="${iprice}">$${iprice}</td>`;
                                const radio = `<td><input class="form-check-input" type="checkbox" name="gridRadios" id="gridRadios1" value="option1" ></td>`
                                return `<tr>${cereCell}${inameCell}${ipriceCell}${radio}</tr>`;
                            }).join('');
                        } else {
                            const cerNo = data['cerNo'];
                            const cerName = data['cerName'];
                            const cereCell = `<td id="${cerNo}">${cerName}</td>`;
                            const itemNo = data['itemNo'];
                            const iname = data['iname'];
                            const inameCell = `<td id="${itemNo}">${iname}</td>`;
                            const iprice = data['iprice'];
                            const ipriceCell = `<td value="${iprice}">$${iprice}</td>`;
                            const radio = `<td><input class="form-check-input" type="checkbox" name="gridRadios" id="gridRadios1" value="option1" ></td>`
                            this.bodyCells = `<tr>${cereCell}${inameCell}${ipriceCell}${radio}</tr>`;
                        }
                        this.tbody.innerHTML = this.bodyCells;

                    });
                })
            }

        },
        showModal() {
            $('#templateModal').modal('show');
        },
        ipriceSubmit() {
            // 找所有被勾選的input
            const checkedBoxes = document.querySelectorAll('input[name="gridRadios"]:checked');
            // 找到父層tr 再找tr下第一個有id的td 取id值 把id值存進array
            const selectedItems = Array.from(checkedBoxes).map(checkbox => checkbox.closest('tr').querySelectorAll('td[id]')[1].id);
            selectedItems.forEach(itemId => {
                fetch(`/item/find?itemNo=${itemId}`, {
                    method: 'GET',
                }).then((response) => {
                    response.json().then(data => {
                        const iprice = data['iprice'];
                        const ipriceKey = itemId + '-price'
                        localStorage.setItem(ipriceKey, iprice)
                    })
                })
                const locKey = itemId + '-Location'
                const dateKey = itemId + '-DateTime'
                this.selectedCereDate = this.selectedCereDate.toString().replace('T', ' ');
                localStorage.setItem(locKey, this.selectedLoc)
                localStorage.setItem(dateKey, this.selectedCereDate)
            })

            // 把tbody的子元素存入array
            const tbodyRows = Array.from(this.tbody.children);
            tbodyRows.forEach(row => {
                const cell = row.querySelectorAll('td[id]');
                const itemId = cell[1].id;
                if (selectedItems.includes(itemId)) {
                    // Create a new row in the detail table
                    const newRow = document.createElement('tr');
                    //舊行列的子元素放進array
                    Array.from(row.children).forEach(cell => {
                        const newCell = document.createElement('td');
                        //選checkbox元素
                        if (cell.querySelector('input[name="gridRadios"]')) {
                            //刪掉checkbox
                            cell.removeChild(cell.querySelector('input[name="gridRadios"]'));
                            const deleteIcon = document.createElement('i');
                            // 放加入class變成刪除icon
                            deleteIcon.classList.add('fa', 'fa-times');
                            // 註冊事件 刪除icon被點的時候刪掉整行
                            deleteIcon.addEventListener('click', () => {
                                newRow.remove();
                            });
                            //刪除icon加在後面
                            newCell.appendChild(deleteIcon);
                        } else {
                            // 其他元素複製到新的cell
                            newCell.innerHTML = cell.innerHTML;
                            // 複製完整的屬性值
                            for (let i = 0; i < cell.attributes.length; i++) {
                                const attr = cell.attributes[i];
                                newCell.setAttribute(attr.name, attr.value);
                            }
                        }
                        newRow.appendChild(newCell);
                    });
                    this.$refs.detailBody.appendChild(newRow);
                } else {
                    this.tbody.removeChild(row);
                }
            });



        },
        totalIprice() {
            let total = 0;
            const rows = this.$refs.detailBody.querySelectorAll('tr');
            const itemIds = [];
            for (let i = 0; i < rows.length; i++) {
                const row = rows[i];
                const cell = row.querySelectorAll('td[id]');
                const inameCell = cell[1];
                const ipriceCell = row.querySelector('td[value]');
                if (ipriceCell) {
                    total += parseInt(ipriceCell.getAttribute('value'));
                    const itemId = inameCell.getAttribute('id')
                    itemIds.push(itemId);
                }
            }
            this.iPrice = total;
            localStorage.setItem('iPrice', total);
            localStorage.setItem('itemIds', JSON.stringify(itemIds));
        },
        addPOrd() {
            fetch('/schedule/addPOrd', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    membno: parseInt(localStorage.getItem('membno')),
                    empno: parseInt(localStorage.getItem('empno')),
                    poDate: localStorage.getItem('poDate'),
                    posta: 1,
                    paysta: 0,
                    poType: parseInt(localStorage.getItem('poType')),
                    dname: localStorage.getItem('dname'),
                    ddate: localStorage.getItem('dDate'),
                    dbirth: localStorage.getItem('dBirth'),
                    tprice: parseInt(localStorage.getItem('iPrice')),
                })
            }).then(response => {
                if (response.ok) {
                    response.json().then(data => { //儲存生成的POrd
                        this.addPOrdDetail(data.pono); // 傳入pono 開始新增PODetail
                    })
                    Swal.fire({
                        position: 'top-end',
                        icon: 'success',
                        title: 'Your work has been saved',
                        showConfirmButton: false,
                        timer: 1500
                    })
                } else {
                    Swal.fire({
                        position: 'top-end',
                        icon: 'error',
                        title: 'failed',
                        showConfirmButton: false,
                        timer: 1500
                    })
                }
            })


        },
        addPOrdDetail(pono) { // 接收從addPOrd傳過來的pono
            const itemnoArr = JSON.parse(localStorage.getItem('itemIds'))
            itemnoArr.forEach(itemId => {
                const locno = localStorage.getItem(itemId + '-Location');
                const datetime = localStorage.getItem(itemId + '-DateTime');
                const iprice = parseInt(localStorage.getItem(itemId + '-price'))
                fetch('/PODetail/addPODetail', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        pono: pono,
                        itemno: itemId,
                        locno: locno,
                        iprice: iprice,
                        date: datetime,
                    }),
                }).then(response => {
                    if (response.ok) {
                        Swal.fire({
                            position: 'top-end',
                            icon: 'success',
                            title: 'Your work has been saved',
                            showConfirmButton: false,
                            timer: 1500
                        })
                    } else {
                        Swal.fire({
                            position: 'top-end',
                            icon: 'error',
                            title: 'failed',
                            showConfirmButton: false,
                            timer: 1500
                        })
                    }
                })
            })
            location.reload();
        },


    }
});








