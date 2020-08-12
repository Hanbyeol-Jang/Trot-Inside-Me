export default{
    URL:'http://i3b202.p.ssafy.io:8080',
    // URL:'http://localhost:8080',
    ROUTES: {
        // auth
        login: '/admin/login',
        logout: '/admin/logout',
        kakaoLogin: '/signin/kakao',
        kakaoLogout: 'https://kauth.kakao.com/oauth/logout?client_id=3b520fb7e5e907ebebfde93be5b8a1aa&logout_redirect_uri=http://i3b202.p.ssafy.io:80/',
        kakaoOff: '/user/kakao/unlink',
        getAmdinUser: '/admin/userNow',
        getUserDetail: '/admin/userNow',

        // singer
        singerList: '/singer',
        singerCreate: '/admin/singer/add',
        singerDelete: '/admin/singer/delete/',

        // program
        programList: '/admin/broadSchedule/list',
        programCreate: '/admin/broadSchedule/add',
        programDelete: '/admin/broadSchedule/delete/',
    }
}