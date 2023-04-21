
new Vue({
    el: '#oPiccontent',
    data() {
        return {
            content: [],
            currentPage: 1,
            pageSize: 5,
            totalPages: 1,
            show: false,
            
        }
    },
    created() {
        // 初始化時顯示第一頁
        this.showTable(this.currentPage);
    },
    methods: {
        showModal() {
            $('#show').modal('show');
        },
        hideModal() {
            this.show = false;
        },
        showTable(pageNum) {
            fetch(`/optPic/findAll?page=${pageNum}&size=${this.pageSize}`, {
                method: 'GET',
            }).then((response) => {
                response.json().then((data) => {
                    this.content = data.content;
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
    },
})