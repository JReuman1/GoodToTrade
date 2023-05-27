<template>
  <div>
    <h2>Iniciar Sesión</h2>
    <form @submit.prevent="login">
      <label for="username">Nombre de usuario:</label>
      <input id="username" v-model="username" type="text" required>

      <label for="password">Contraseña:</label>
      <input id="password" v-model="password" type="password" required>

      <button type="submit">Iniciar Sesión</button>
    </form>
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
        this.$router.push('/products'); // redirige a la página de productos después de iniciar sesión
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>
