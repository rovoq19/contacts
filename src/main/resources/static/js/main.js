function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}

var contactApi = Vue.resource('/contact{/id}');

Vue.component('contact-form',{
    props: ['contacts', 'contactAttr'],
    data: function(){
        return{
            contactName: '',
            contactPhone: '',
            id: ''
        }
    },
    watch: {
        contactAttr: function(newVal, oldVal){
            this.contactName=newVal.contactName;
            this.contactPhone=newVal.contactPhone;
            this.id=newVal.id;
        }
    },
    template:
        '<div>' +
            '<input type="text" placeholder="Имя" v-model="contactName" />' +
            '<input type="text" placeholder="Номер телефона" v-model="contactPhone" />' +
            '<input type="button" value="Сохранить" @click="save" style="margin: 5px"/>' +
        '</div>',
    methods: {
        save: function(){
            var contact = { contactName: this.contactName , contactPhone: this.contactPhone };

            if(this.id){
                contactApi.update({id: this.id}, contact).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.contacts, data.id);
                        this.contacts.splice(index, 1, data);
                        this.contactName = '';
                        this.contactPhone = '';
                        this.id = '';
                    })
                )
            } else{
                contactApi.save({}, contact).then(result =>
                    result.json().then(data => {
                        this.contacts.push(data);
                        this.contactName = '';
                        this.contactPhone = '';
                    })
                )
            }
        }
    }
});

Vue.component('contact-row', {
  props: ['contact', 'editMethod', 'contacts'],
  template:
    '<div style="padding-top: 10px">' +
        '<div>{{ contact.id }}. Имя: {{ contact.contactName }}</div>' +
        '<div> Номер: {{ contact.contactPhone }} </div>' +
        '<span style="absolute: absolute; left: 200px; display: block">' +
            '<input type="button" value="Редактировать" @click="edit" style="margin: 5px"/>' +
            '<input type="button" value="Удалить" @click="del" style="margin: 5px"/>' +
        '</span>'+
    '</div>',
  methods: {
    edit: function(){
        this.editMethod(this.contact);
    },
    del: function(){
        contactApi.remove({id: this.contact.id}).then(result =>{
            if(result.ok){
                this.contacts.splice(this.contacts.indexOf(this.contact), 1)
            }
        })
    }
  }
});

Vue.component('contacts-list', {
  props: ['contacts'],
  data: function(){
    return {
        contact: null
    }
  },
  template:
    '<div style="position: relative; width: 300px;">' +
        '<contact-form :contacts="contacts" :contactAttr="contact" />' +
        '<contact-row v-for="contact in contacts" :key="contact.id" :contact="contact" :editMethod="editMethod" :contacts="contacts"/>' +
    '</div>',
  methods: {
    editMethod: function(contact){
        this.contact = contact;
    }
  }
});

var app = new Vue({
  el: '#app',
  template:
      '<div>' +
        '<div v-if="!profile"><a href="/login">Авторизация через Google</a></div>' +
        '<div v-else>'+
            '<div>{{profile.name}}&nbsp;<a href="/logout">Выйти</a></div>' +
            '<contacts-list :contacts="contacts" />' +
        '</div>'+
      '</div>',
  data: {
    contacts: frontendData.contacts,
    profile: frontendData.profile
  }
});