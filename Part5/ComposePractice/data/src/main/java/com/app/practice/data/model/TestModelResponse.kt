package com.app.practice.data.model

// 아무리 서버랑 협약해서
// 이 데이터는 non-nullable하게 보내주세요 라고 하더라도
// 오류가 생겨서 null을 받을 수 있기 때문에
// 최대한 선언 시에 nullable로 선언하는 것이 좋다...
class TestModelResponse(val name: String?) {

}

// Extension function
fun TestModelResponse.toDomainModel() {
    // 유효성 체크
    // 서버 상황이 어떻든 안정성 확보 가능
    // 기본적으로 앱 서비스에서 서버를 믿지 않는 다는 입장으로 구현하는 것이 최선
    return if (name != null) TestModel(this.name) else null
}