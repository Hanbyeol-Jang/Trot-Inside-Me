export default{
    URL:'https://i3b202.p.ssafy.io:8080',
    //URL:'https://localhost:8080',
    ROUTES: {
        // auth
        login: '/admin/login',
        logout: '/admin/logout',
        kakaoLogin: '/signin/kakao',
        kakaoLogout: 'https://kauth.kakao.com/oauth/logout?client_id=3b520fb7e5e907ebebfde93be5b8a1aa&logout_redirect_uri=https://i3b202.p.ssafy.io:443/',
        //kakaoLogout: 'https://kauth.kakao.com/oauth/logout?client_id=3b520fb7e5e907ebebfde93be5b8a1aa&logout_redirect_uri=http://localhost:8081/',
        kakaoOff: '/user/kakao/unlink',
        getUserInfo: '/user/getUserInfo',

        // test

        // singer
        singerList: '/board/singerlist',
        singerDetail: '/board/singerdetail/',
        singerCreate: '/admin/singeradd',
        singerDelete: '/admin/singerdelete/',
        singerVideoList: '/board/videolist/',
        singerArticleList: '/board/articlelist/',
        singerScheduleList: '/board/schedule/singerScheduleList/',

        // main
        mainList: '/board/mainlist/',
        
        // follow
        follow: '/user/follow/',
        followSingersList: '/user/followlist/',
        followVideoList: '/user/videolist/',
        followArticleList: '/user/articlelist/',

        // program
        programList: '/admin/tvlist',
        programCreate: '/admin/tvadd',
        programDelete: '/admin/tvdelete/',
        programSchedule: '/board/schedule/todayList',

        // vote
        thisWeekTopThree: '/ThisWeekTopThree',
        lastWeekTopThree: '/lastWeekTopThree',

    }
}
