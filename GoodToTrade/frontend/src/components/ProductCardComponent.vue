<template>
  <div class="d-flex justify-content-center align-items-center" style="height: 100vh;">
    <tinder
      v-if="products.length > 0"
      ref="tinder"
      @like="like"
      @dislike="dislike"
      class="w-30 h-30"
    >
      <tinder-card class="product-card w-100 h-100" v-for="product in products" :key="product.id">
        <div
          class="bg-image w-100 h-100 d-flex flex-column justify-content-center align-items-center"
          :style="{ backgroundImage: `url(${product.imageUrl})` }"
        >
          <div class="product-info text-center">
            <h2 class="text-black">{{ product.productName }}</h2>
          </div>
          <div class="actions d-flex justify-content-center mt-3">
              <button class="btn dislike" @click="dislike"></button>
              <button class="btn like" @click="like(product.id)"></button>
          </div>
        </div>
      </tinder-card>
    </tinder>
  </div>
</template>

  
  <script>
  import axios from "axios";
  import { Tinder, TinderCard } from "vue-tinder";
  
  export default {
    components: {
      Tinder,
      TinderCard,
    },
    data() {
      return {
        products: [],
      };
    },
    async created() {
      try {
        const response = await axios.get("http://localhost:8080/api/products");
        this.products = response.data;
      } catch (error) {
        console.error(error);
      }
    },
    methods: {
      dislike() {
        this.products.splice(this.$refs.tinder.index, 1);
      },
      async like(productId) {
        try {
          const response = await axios.post(
            "http://localhost:8080/api/likes",
            {
              userId: "1", // Id del usuario actual (debes proporcionar el valor correcto)
              productId: productId,
            },
            {
              headers: {
                Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
              },
            }
          );
          console.log(response.data);
          this.products.splice(this.$refs.tinder.index, 1); // Eliminar la foto de la lista
        } catch (error) {
          console.error(error);
        }
      },
    },
  };
  </script>
 <style scoped>
 .product-card {
   /* Tamaño y alineación */
   width: 100%;
   height: 100%;
   display: flex;
   flex-direction: column;
   justify-content: center;
   align-items: center;
 }
 
 .btn {
   /* Botones de like y dislike */
   width: 100px;
   height: 100px;
   border-radius: 50%;
   background-repeat: no-repeat;
   background-position: center;
   background-size: contain;
   border: none;
 }
 
 .dislike {
   /* Imagen del botón de dislike */
   background-image: url('https://uploads.codesandbox.io/uploads/user/992079af-4d21-44ac-8853-43908c0d9b78/B5S4-nope.png');
 }
 
 .like {
   /* Imagen del botón de like */
   background-image: url('https://uploads.codesandbox.io/uploads/user/992079af-4d21-44ac-8853-43908c0d9b78/B3NV-like.png');
 }
 
 .actions {
   /* Alineación de los botones */
   position: absolute;
   bottom: 10px;
   display: flex;
   justify-content: center;
   gap: 20px;
   width: 100%;
 }
 
 .bg-image {
   /* Imagen de fondo */
   background-size: cover;
   background-position: center;
   background-repeat: no-repeat;
   width: 45%;
   height: 100%;
   position: relative;
   border-radius: 20px;
 }
 
 .product-info h2 {
   /* Estilos para el título del producto */
   color: white;
   font-weight: bold;
   text-align: center;
 }
 
 </style>
 