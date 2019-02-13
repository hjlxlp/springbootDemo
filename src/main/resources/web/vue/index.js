var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello Vue!'
    }
});

var vm = new Vue({
    el: '#example',
    data: {
        message: 'Hello'
    },
    computed: {
        // 一个 computed 属性的 getter 函数
        reversedMessage: function () {
            // `this` 指向 vm 实例
            return this.message.split('').reverse().join('')
        }
    }
})

var watchExampleVM = new Vue({
    el: '#watch-example',
    data: {
        question: '',
        answer: '你要先提出问题，我才能给你答案！'
    },
    watch: {
        // 只要 question 发生改变，此函数就会执行
        question: function () {
            this.answer = '等待输入停止……' + this.question
            if (this.question.indexOf('?') === -1) {
                this.answer = '问题通常需要包含一个问号。;-)'
                return
            }
            this.answer = '思考中……'
        }
    }
})

var ob = new Vue({
    el: '#v-for-object',
    data: {
        object: {
            firstName: 'John',
            lastName: 'Doe',
            age: 30
        }
    }
})

var example1 = new Vue({
    el: '#example-1',
    data: {
        counter: 0
    },
    methods: {
        count: function () {
            return this.counter += 1
        }
    }
})

var input = new Vue({
    el: '#input',
    data: {
        message: '呵呵哒',
        age: 11
    },
})


Vue.component('button-counter', {
    data: function () {
        return {
            count: 0
        }
    },
    template: '<button v-on:click="count++">你点击了 {{ count }} 次。</button>'
})

Vue.component('blog-post', {
    props: ['title'],
    template: '<h3>{{ title }}</h3>'
})

new Vue({ el: '#components-demo' })

