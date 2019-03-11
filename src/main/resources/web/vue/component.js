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

Vue.component('blog-post-two', {
    props: ['post'],
    template: '<h3>{{ post.title }}</h3>'
})

Vue.component('blog-post-three', {
    props: ['post'],
    template: '<div class="blog-post"><h3>{{ post.title }}</h3><button v-on:click="$emit(\'enlarge-text\', 0.1)">放大文本</button><div v-html="post.content"></div></div>'
})

Vue.component('custom-input', {
    props: ['value'],
    template: '<input v-bind:value="value" v-on:input="$emit(\'input\', $event.target.value)">'
})


new Vue({
    el: '#components-demo',
    data: {
        posts: [
            {id: 1, title: '我的 Vue 旅程'},
            {id: 2, title: '用 Vue 写博客'},
            {id: 3, title: 'Vue 如此有趣'}
        ],
        postFontSize: 1,
        searchText: 1
    },
    methods: {
        onEnlargeText: function (enlargeAmount) {
            this.postFontSize += enlargeAmount
        }
    }
})