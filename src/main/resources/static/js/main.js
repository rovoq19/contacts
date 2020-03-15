var userApi = Vue.resource('/user{/id}');

Vue.component('user-row', {
  props: ['user'],
  template: '<div><i>({{ user.id }})</i>{{ user.name }}</div>'
});

Vue.component('users-list', {
  props: ['users'],
  template: '<div><user-row v-for="user in users" :key="user.id" :user="user" /></div>',
  created: function(){
    userApi.get().
    then(result => result.json().
    then(data => data.forEach(user => this.users.push(user)))
    )
  }
});

var app = new Vue({
  el: '#app',
  template: '<users-list :users="users" />',
  data: {
    users: []
  }
});