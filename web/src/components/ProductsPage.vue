<script setup>
import {ref} from "vue";

const listData = ref([])

fetch('http://localhost:8080/product/0/20', {
  method: 'GET',
})
  .then((resp) => resp.json())
  .then(({content}) => listData.value = content)

function updateCard(item) {
  const obj = { ...item }
  fetch('http://localhost:8080/product', {
    method: 'POST',
    headers: {'content-type': 'application/json;'},
    body: JSON.stringify(obj)
  })
}

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
        <v-card width="304px" v-for="item in listData" :key="item.id">
          <v-card-title  class="w-100 d-flex justify-space-between">
            <span class="d-inline-block font-weight-bold">{{item.name}}</span>
            <span class="d-inline-block font-italic">{{item.quantity + ' ед.'}}</span>
          </v-card-title>
          <v-card-text>{{`Скорость использования ${item.speed} ед. в день`}}</v-card-text>
          <v-card-text>
            <v-text-field variant="outlined" label="Эвристический буфер" v-model="item.bufer" @update:modelValue="updateCard(item)"></v-text-field>
            <v-text-field variant="outlined" label="Мин. значение" v-model="item.minValue"></v-text-field>
            <v-text-field variant="outlined" label="Макс. значение" v-model="item.maxValue"></v-text-field>
          </v-card-text>
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
