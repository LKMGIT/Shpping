<script setup>
import { useRoute } from "vue-router";
import { reactive, computed } from "vue";
import { getItem } from "@/services/itemService.js";

const route = useRoute();

const state = reactive({
  item: {
    id: 0,
    name: "",
    imgPath: "",
    price: 0,
    discountPer: 0,
    created: ""
  }
});

// 할인 가격
const discountPrice = computed(() => {
  const price = state.item.price || 0;
  const per = state.item.discountPer || 0;
  const discounted = price - (price * per / 100);
  return discounted.toLocaleString() + "원";
});

// 커스텀 생성 훅
(async function onCreated() {
  const id = Number(route.params.id);
  const res = await getItem(id);

  if (res.status === 200) {
    state.item = res.data;
  }
})();
</script>

<template>
  <div class="item-detail">
    <div class="container py-5" v-if="state.item.id">
      <div class="row justify-content-center">
        <div class="col-lg-10 col-xl-8">
          <div class="card shadow-sm border-0 overflow-hidden">
            <div class="row g-0">
              <!-- 왼쪽: 이미지 -->
              <div class="col-md-5">
                <div
                    class="thumb"
                    :style="{ backgroundImage: `url(${state.item.imgPath})` }"
                    :aria-label="`상품 사진(${state.item.name})`"
                ></div>
              </div>

              <!-- 오른쪽: 정보 -->
              <div class="col-md-7">
                <div class="card-body d-flex flex-column h-100">
                  <p class="text-uppercase text-muted small mb-1">
                    상품 상세
                  </p>
                  <h1 class="h4 fw-semibold mb-3">
                    {{ state.item.name }}
                  </h1>

                  <div class="mb-3">
                    <span class="badge bg-danger me-2">
                      -{{ state.item.discountPer }}%
                    </span>
                    <span class="text-muted text-decoration-line-through me-2">
                      {{ state.item.price.toLocaleString() }}원
                    </span>
                    <span class="h5 text-danger fw-bold">
                      {{ discountPrice }}
                    </span>
                  </div>

                  <hr class="my-3" />

                  <p class="text-muted small mb-0">
                    등록일: {{ state.item.created }}
                  </p>

                  <div class="mt-auto pt-3 d-flex justify-content-end gap-2">
                    <router-link to="/" class="btn btn-outline-secondary btn-sm">
                      목록으로
                    </router-link>
                  </div>

                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<style scoped lang="scss">
.item-detail .thumb {
  width: 100%;
  aspect-ratio: 1 / 1;
  background-size: cover;
  background-position: center;
  border : solid black;
  border-radius: 0.75rem;
  overflow: hidden;
}
</style>
