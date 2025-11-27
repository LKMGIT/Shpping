<script setup>
import {useAccountStore} from "@/stores/account";
import {logout} from "@/services/accountService";
import {useRouter} from "vue-router";

// 계정 스토어
const accountStore = useAccountStore();

// 라우터 객체
const router = useRouter(); // ②

// 로그아웃
// 로그아웃
const logoutAccount = async () => { // ③
  const res = await logout();

  if (res.status === 200) {
    accountStore.setLoggedIn(false);
    await router.push("/");
  }
};
</script>

<template>
  <header>
    <div class="navbar navbar-dark bg-dark text-white shadow-sm">
      <div class="container">
        <router-link to="/" class="navbar-brand">
          <img src="/img/logo.png" alt="타건 로고" class="logo-img" />
          <strong class="best-key-logo">베스트키</strong>
        </router-link>
        <div class="menus d-flex gap-3">
          <template v-if="!accountStore.loggedIn">  <!-- ④ -->
            <router-link to="/login">로그인</router-link>
            <router-link to="/join">회원가입</router-link>
          </template>
          <template v-else>
            <a @click="logoutAccount()">로그아웃</a>
            <router-link to="/orders">주문 내역</router-link>
            <router-link to="/cart">장바구니</router-link>
          </template>
        </div>
      </div>
    </div>
  </header>
</template>

<style lang="scss">
header {
  .menus {
    a { // ⑦
      cursor: pointer;
      color: #fff;
      text-decoration: none;
    }
  }
  .logo-img {
    height: 50px;        /* 32px → 24px 같이 더 작게 */
    width: auto;         /* 가로는 비율에 맞게 자동 */
  }
  .best-key-logo {
    font-weight: 800;
    font-size: 1.4rem;         /* 크기 조절하고 싶으면 여기만 바꿔 */
    letter-spacing: 0.15em;    /* 자간 넓혀서 로고 느낌 */
    text-transform: uppercase; /* 영문일 때는 대문자, 한글은 그대로 */

    /* 그라디언트 텍스트 */
    background: linear-gradient(120deg, #007bff, #6610f2);
    -webkit-background-clip: text;
    color: transparent;

    /* 살짝 입체감 */
    text-shadow: 0 2px 6px rgba(0, 0, 0, 0.18);
    white-space: nowrap;
  }


}
</style>