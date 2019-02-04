<template>

  <div class="user">

    <h1>{{ msg }}</h1>
    <h2>Just some database interaction...</h2>

    <input type="text" v-model="inputUser.firstName" placeholder="first name">
    <input type="text" v-model="inputUser.lastName" placeholder="last name">

    <button @click="callCreateUserRestService()">Create User</button>

    <div v-if="showResponse"><h6>User created with Id: {{ response }}</h6></div>

    <button v-if="showResponse" @click="callRetrieveUserRestService()">Retrieve user {{inputUser.id}} data from
      database
    </button>

    <h4 v-if="showRetrievedUser">Retrieved User {{retrievedUser.firstName}} {{retrievedUser.lastName}}</h4>

  </div>

</template>

<script>
  // import axios from 'axios'
  import {AXIOS} from './http-common'

  export default {
    name: "user",

    data() {
      return {
        msg: 'Create User:',
        inputUser: {
          firstName: '',
          lastName: '',
          id: 0
        },
        response: [],
        errors: [],
        showResponse: false,
        retrievedUser: {},
        showRetrievedUser: false
      }
    },
    methods: {
      // Fetches posts when the component is created.
      callCreateUserRestService() {

        var params = new URLSearchParams()
        params.append('firstName', this.inputUser.firstName)
        params.append('lastName', this.inputUser.lastName)

        AXIOS.post(`/user`, params)
          .then(response => {
            // JSON responses are automatically parsed.
            this.response = response.data
            this.inputUser.id = response.data
            console.log(response.data)
            this.showResponse = true
          })
          .catch(e => {
            this.errors.push(e)
          })
      },

      callRetrieveUserRestService() {
        AXIOS.get(`/user/` + this.inputUser.id)
          .then(response => {
            // JSON responses are automatically parsed.
            this.retrievedUser = response.data
            console.log(response.data)
            this.showRetrievedUser = true
          })
      }
    }
  }
</script>

<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
