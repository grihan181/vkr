<script setup>

</script>

<template>
  <v-tabs-window-item :value="1">
    <div class="container-pa">
      <v-text-field
        variant="solo"
        width="468px"
        class="input-pa"
        append-inner-icon="mdi-magnify"
      ></v-text-field>
      <div class="list d-flex justify-space-between flex-wrap ga-5">
        <v-card width="304px" height="234px">
          <v-card-title>Двигатель</v-card-title>
          <v-card-text>Скорость использования 5 ед. в день</v-card-text>
          <v-card-text>
            <v-text-field label="Эвристический буфер" ></v-text-field>
            <v-text-field label="Эвристический буфер" width="100"></v-text-field>
            <v-text-field label="Эвристический буфер" width="100"></v-text-field>
          </v-card-text>
        </v-card>
        <v-card width="304px" height="234px">

        </v-card>
        <v-card width="304px" height="234px">

        </v-card>
        <v-card width="304px" height="234px">

        </v-card>
        <v-card width="304px" height="234px">

        </v-card>
        <v-card width="304px" height="234px">

        </v-card>
        <v-card width="304px" height="234px">

        </v-card>
        <v-card width="304px" height="234px">

        </v-card>
        <v-card width="304px" height="234px">

        </v-card>
      </div>
    </div>
  </v-tabs-window-item>
</template>

<style scoped>
.container-pa {
  padding: 12px 240px;
}

.input-pa {
  padding: 12px 0;
}
</style>
