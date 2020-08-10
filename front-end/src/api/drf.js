export default{
    // URL:'http://i3b202.p.ssafy.io:8080',
    URL:'http://localhost:8080',
    ROUTES: {
        // auth
        login: '/admin/login',
        logout: '/admin/logout',
        kakaoLogin: '/signin/kakao',
        kakaoLogout: 'https://kauth.kakao.com/oauth/logout?client_id=78183e66919b34b25f731ea9f2d99f0e&logout_redirect_uri=http://localhost:8081/',
        
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