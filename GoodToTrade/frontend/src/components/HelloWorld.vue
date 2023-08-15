<template>
    <div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
      <div class="col-md-6">
        <h2 class="mb-4 text-center text-black">Iniciar Sesión</h2>
        <form @submit.prevent="login" class="needs-validation">
          <div class="form-group">
            <label for="username" class="text-black">Nombre de usuario:</label>
            <input id="username" v-model="username" type="text" class="form-control" placeholder="Nombre de usuario" required>
          </div>
  
          <div class="form-group">
            <label for="password" class="text-black">Contraseña:</label>
            <input id="password" v-model="password" type="password" class="form-control" placeholder="Contraseña" required>
          </div>
  
          <button type="submit" class="btn btn-primary">Iniciar Sesión</button>
        </form>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        username: '',
        password: '',
      };
    },
    methods: {
      async login() {
        try {
          const response = await axios.get(`http://localhost:8080/api/login?username=${this.username}&password=${this.password}`);
          localStorage.setItem('accessToken', response.data.access_token);
          const accessToken = localStorage.getItem('accessToken');
          if (accessToken) {
            // El token de acceso está presente en el local storage
            console.log('El token de acceso está almacenado en el local storage');
            this.$router.push('/products'); // redirige a la página de productos después de iniciar sesión
          } else {
            // El token de acceso no está presente en el local storage
            console.log('El token de acceso no está almacenado en el local storage');
          }
        } catch (error) {
          console.error(error);
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .container {
    background-color: #f8f9fa;
  }
  
  .text-black {
    color: #000000;
  }
  
  .btn-primary {
    background-color: #007bff;
    border-color: #007bff;
  }
  
  .btn-primary:hover {
    background-color: #0069d9;
    border-color: #0062cc;
  }
  
  .form-control {
    border-color: #000000;
    color: #000000;
  }
  
  .form-control:focus {
    border-color: #000000;
    box-shadow: none;
  }
  </style>
  