export default{
    // URL:'http://i3b202.p.ssafy.io:8080',
    URL:'http://localhost:8080',
    ROUTES: {
        // auth
        login: '/admin/login',
        logout: '/admin/logout',
        kakaoLogin: '/signin/kakao',
        kakaoLogout: 'https://kauth.kakao.com/oauth/logout?client_id=3b520fb7e5e907ebebfde93be5b8a1aa&logout_redirect_uri=http://i3b202.p.ssafy.io:80/',
        kakaoOff: '/user/kakao/unlink',
        getUserInfo: '/user/getUserInfo',

        // singer
        singerList: '/singer',
        singerCreate: '/admin/singeradd',
        singerDelete: '/admin/singerdelete/',

        // program
        programList: '/admin/tvlist',
        programCreate: '/admin/tvadd',
        programDelete: '/admin/tvdelete/',
    }
}