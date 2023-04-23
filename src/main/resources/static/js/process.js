new Vue({
    el: '#process',
    data() {
        return {
            currentPage: 1,
            pageSize: 1000,
            totalPages: 1,
            religions: [],
            ceremonies: [],
            allCeremonies: [],
            processes: [],
            searchTerm: '',
            addRelNo: 0,
            addCerNo: 0,
            addProName: '',
            addProSta: false,
            addProSeq: '',
            newRelNo: '',
            newCerNo: '',
            newProName: '',
            newProSta: '',
            newProSeq: '',
            newRelName: '',
        }
    },
    created() {
        this.showTable(this.currentPage);
        fetch('/rel/findAll', {
            method: 'GET',
        }).then(response => response.json())
            .then(data => {
                this.religions = data;
            });
        this.getAllCeremonies();

        // 監聽newCerNo的變化，當選擇儀式變更時，動態取得宗教名稱
        this.$watch('newCerNo', (newValue, oldValue) => {
            if (newValue !== 0) {
                fetch(`/ceremony/findRelName?cerNo=${newValue}`)
                    .then(response => response.json())
                    .then(data => {
                        this.newRelName = data.join(''); // 將取得的宗教名稱設定到newRelName中
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
        addProcess() {
            fetch('/pro/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    proSeq: this.addProSeq,
                    proName: this.addProName,
                    proSta: this.addProSta,
                    cerNo: this.addCerNo
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
        getCeremonies() {
            fetch(`ceremony/ceremoniesByRelno?relNo=${this.addRelNo}`, {
                method: 'GET',
            }).then(response => {
                response.json().then(data => {
                    this.ceremonies = data;
                });
            });
        },
        showTable() {
            fetch(`/pro/findCereName`, {
                method: 'GET',
            }).then((response) => {
                response.json().then((data) => {
                    this.processes = data;
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
        showUpdateModal(pro) {
            $('#updateModal').modal('show')
            this.newProSeq = pro.proSeq;
            this.newProName = pro.proName;
            this.newProNo = pro.proNo;
            this.newCerName = pro.cerName;
            this.newCerNo = pro.cerNo;
            this.newProSta = pro.proSta

        },
        showModal() {
            $('#processModal').modal('show')
        },
        updatePro() {
            fetch(`/pro/update/${this.newProNo}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    proNo: this.newProNo,
                    proName: this.newProName,
                    proSta: this.newProSta,
                    cerNo: this.newCerNo,
                    proSeq: this.newProSeq
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
        getAllCeremonies() {
            fetch(`/ceremony/findAll?page=1&size=100000`, {
                method: 'GET',
            }).then((response) => {
                response.json().then((data) => {
                    this.allCeremonies = data.content;
                    this.totalPages = data.totalPages;
                });
            });
        }
    },
})
