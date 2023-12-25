<template>
    <div>
        <!-- 头部 -->
        <Header />
        <div style="display: flex">
            <!-- 侧边栏 -->
            <Aside />
            <!-- 内容区域 -->
            <router-view style="flex: 1"/>
        </div>

    </div>
</template>

<script>
    import Header from "@/components/Header/Header";
    import Aside from "@/components/Aside/Aside";
    export default {
        name: "Layout",
        components:{
            Header,
            Aside
        }
    }
</script>

<style scoped>

</style>
