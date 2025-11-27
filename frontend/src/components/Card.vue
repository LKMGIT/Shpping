<script setup>
import {addItem} from "@/services/cartService";
import {useRouter} from "vue-router";
import {computed} from "vue";

const props = defineProps({
  item: {
    id: Number,
    imgPath: String,
    name: String,
    price: Number,
    discountPer: Number
  }
});

// 상품 할인가
const computedItemDiscountPrice = computed(() => {
  return (props.item.price - (props.item.price * props.item.discountPer / 100)).toLocaleString() + '원';
})

// 라우터 객체
const router = useRouter(); // ①

// 장바구니에 상품 담기
const put = async () => { // ②
  const res = await addItem(props.item.id);

  if (res.status === 200 && window.confirm('장바구니에 상품을 담았습니다. 장바구니로 이동하시겠습니까?')) {
    await router.push("/cart");
  }
};
</script>

<template>
  <div class="card shadow-sm">
    <!-- 상품 사진 출력 -->
    <router-link :to="`/item/${props.item.id}`">
    <span class="img" :style="{backgroundImage: `url(${props.item.imgPath})`}"
          :aria-label="`상품 사진(${props.item.name})`"></span>
    </router-link>
    <div class="card-body">
      <p class="card-text">
        <!-- 상품 이름 -->
        <span class="me-2">{{ props.item.name }}</span>
        <!-- 상품 할인율 -->
        <span class="discount badge bg-danger">{{ props.item.discountPer }}%</span>
      </p>
      <div class="d-flex justify-content-between align-items-center">
        <button class="btn btn-primary btn-sm" @click="put()">장바구니 담기</button>
        <!-- 상품 정가(숫자 데이터에 3자리마다 쉼표 표기) -->
        <small class="price text-muted">{{ props.item.price.toLocaleString() }}원</small>
        <!-- 상품 할인가 -->
        <small class="real text-danger">{{ computedItemDiscountPrice }}</small>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.card {
  /* 카드 안쪽 여백 제거 + 모서리 잘리게 */
  padding: 0.75rem;
  border-radius: 0.75rem;
  overflow: hidden;  // 이미지가 카드 밖으로 튀어나가지 않게

  .img {
    display: block;
    width: 100%;

    /* 이미지 영역 비율(정사각형). 필요하면 4 / 3, 3 / 2 등으로 조절 */
    aspect-ratio: 1 / 1;

    /* 카드 안을 꽉 채우도록 */
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
  }

  .card-body {
    padding: 0.75rem 0.9rem 1rem;  // 텍스트 쪽만 여백
  }

  .card-body .price {
    text-decoration: line-through;
  }
}
</style>
