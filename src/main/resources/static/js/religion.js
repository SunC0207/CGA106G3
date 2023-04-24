new Vue({
    el: '#religion',
    data() {
        return {
            newRelName: '',
            newRelNo:'',
            religions: [],
            searchTerm:'',

        }
    },
    computed: {
        regex() {
            return new RegExp(this.searchTerm, "i");
        }
    },
    created() {
        this.showAll();
    },
    methods: {
        showModal() {
            $('#religionModal').modal('show');
        },
        showAll() {
            fetch(`/rel/findAll`, {
                method: 'GET',
            }).then((response) => {
                response.json().then(data => {
                        this.religions = data;
                })
            })
        },
        addReligion() {
            let reg = /^[\u4E00-\u9FA5]{1,10}$/;
            if (reg.test(this.newRelName)) {
                fetch('/rel/add', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        relName: this.newRelName
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
            } else {
                Swal.fire({
                    icon: 'error',
                    title: '請輸入10個以內的中文字',
                    text: '',
                    confirmButtonText: 'OK'
                })
            }
            this.newRelName = '';
        },
        showUpdateModal(rel) {
            $('#updateModal').modal('show');
            this.newRelName = rel.relName;
            this.newRelNo = rel.relNo;

        },
        updateRel() {
            fetch(`/rel/update/${this.newRelNo}`,{
                method: 'PUT',
                headers: {
                    'Content-Type' : 'application/json'
                },
                body: JSON.stringify({
                    relName: this.newRelName
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

    },
})












