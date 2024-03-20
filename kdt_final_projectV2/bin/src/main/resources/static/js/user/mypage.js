document.addEventListener('DOMContentLoaded', function() {
  const sideMenuNav = document.querySelector('.side-menu-nav');
  const sideMenuForms = document.querySelectorAll('.side-menu-form');
  const recentVisitedPagesList = document.getElementById('recent-visited-pages-list'); // 최근 방문한 페이지 목록 요소

  sideMenuNav.addEventListener('click', function(event) {
    event.preventDefault();
    if (event.target.tagName === 'A') {
      const targetSectionId = event.target.getAttribute('href');
      showSection(targetSectionId);
    }
  });

  // showSection 함수에서 '#section7' 섹션만 보여주도록 수정
  function showSection(targetSectionId) {
    sideMenuForms.forEach(function(form) {
      if ('#' + form.id === targetSectionId) {
        form.style.display = 'block';
      } else {
        form.style.display = 'none';
      }
    });
  }

  // 회원 탈퇴 확인 함수
  function confirmWithdraw() {
    // confirm 메서드를 사용하여 사용자에게 정말로 탈퇴할 것인지 확인
    return confirm("정말로 회원 탈퇴하시겠습니까?");
  }

  // 회원 탈퇴가 성공적으로 이루어진 경우 메인 페이지로 이동하는 함수
  function redirectToMainPage() {
    window.location.href = "/"; // 메인 페이지 URL로 리다이렉트
  }

  // 서버로부터 받은 응답에 따라 처리를 수행하는 함수
  function handleWithdrawResponse(data) {
    alert(data); // 서버로부터 받은 응답 메시지 출력
    if (data === "탈퇴되었습니다.") {
      redirectToMainPage(); // 회원 탈퇴 성공 시 메인 페이지로 이동
    }
  }

  // 회원 탈퇴 버튼 클릭 시 이벤트 핸들러
  const withdrawForm = document.querySelector('.withdraw-section form');
  withdrawForm.addEventListener('submit', function(event) {
    event.preventDefault();
    if (confirmWithdraw()) {
      // 회원 탈퇴 요청을 비동기적으로 서버로 보냄
      fetch('/withdrawUser', {
          method: 'POST',
      })
      .then(response => response.text())
      .then(data => handleWithdrawResponse(data))
      .catch(error => {
          console.error('Error:', error);
      });
    }
  });

  // 최근 방문한 페이지 섹션을 보여주고 목록을 가져오기
  function showRecentVisitedPagesSection() {
    fetch('/getRecentVisitedPages')
      .then(response => {
        if (response.ok) {
          return response.json(); // JSON 형식으로 파싱된 데이터 반환
        }
        throw new Error('Error: ' + response.status);
      })
      .then(data => {
        clearRecentVisitedPages();
        if (data && Array.isArray(data)) {
          data.forEach(visitedPage => {
            const listItem = document.createElement('li');
            listItem.textContent = visitedPage.pageUrl;
            recentVisitedPagesList.appendChild(listItem);
          });
        }
        // 최근 방문한 페이지 섹션을 보여줌
        recentVisitedPagesSection.style.display = 'block';
      })
      .catch(error => {
        console.error('Error:', error);
      });
  }

  // 최근 방문한 페이지 섹션 링크 클릭 시 이벤트 핸들러
  const recentVisitedPagesLink = document.querySelector('.side-menu-nav a[href="#section4"]');
  recentVisitedPagesLink.addEventListener('click', function(event) {
    event.preventDefault();
    showRecentVisitedPagesSection();
  });

// 글쓴 내역을 AJAX로 가져오는 함수
function getWrittenPosts() {
  fetch('/getWrittenPosts')
    .then(response => {
      if (response.ok) {
        return response.json();
      }
      throw new Error('Error: ' + response.status);
    })
    .then(data => {
      const tableBody = document.getElementById('boardTableBody');
      tableBody.innerHTML = ''; // 기존의 내용을 초기화

      if (data && Array.isArray(data)) {
        data.forEach(item => {
          const row = document.createElement('tr');
          const titleCell = document.createElement('td');
          titleCell.classList.add('board-title');
          titleCell.textContent = item.title;
          const contentsCell = document.createElement('td');
          contentsCell.classList.add('board-contents');
          contentsCell.textContent = item.contents;
          const boardTitleCell = document.createElement('td');
          boardTitleCell.classList.add('board-board_title');
          boardTitleCell.textContent = item.board_title;
          const placeCell = document.createElement('td');
          placeCell.classList.add('board-place');
          placeCell.textContent = item.place;
          const writerCell = document.createElement('td');
          writerCell.classList.add('board-writer');
          writerCell.textContent = item.writer;
          const writingtimeCell = document.createElement('td');
          writingtimeCell.classList.add('board-writingtime');
          writingtimeCell.textContent = item.writingtime;

          row.appendChild(titleCell);
          row.appendChild(contentsCell);
          row.appendChild(boardTitleCell);
          row.appendChild(placeCell);
          row.appendChild(writerCell);
          row.appendChild(writingtimeCell);

          tableBody.appendChild(row);
        });
      } else {
        const row = document.createElement('tr');
        const emptyCell = document.createElement('td');
        emptyCell.setAttribute('colspan', '6');
        emptyCell.textContent = '글쓴 내역이 없습니다.';
        row.appendChild(emptyCell);
        tableBody.appendChild(row);
      }
    })
    .catch(error => {
      console.error('Error:', error);
    });
}

// 글쓴 내역 섹션 링크 클릭 시 이벤트 핸들러
const writtenPostsLink = document.querySelector('.side-menu-nav a[href="#section2"]');
writtenPostsLink.addEventListener('click', function(event) {
  event.preventDefault();
  getWrittenPosts(); // 글쓴 내역 가져오는 함수 호출
});

});

// 댓글 쓴 내역을 AJAX로 가져오는 함수
function getCommentListByWriter() {
  fetch('/getCommentListByWriter')
    .then(response => {
      if (response.ok) {
        return response.json();
      }
      throw new Error('Error: ' + response.status);
    })
    .then(data => {
      const tableBody = document.getElementById('commentsTableBody');
      tableBody.innerHTML = ''; // 기존의 내용을 초기화

      if (data && Array.isArray(data)) {
        data.forEach(item => {
          const row = document.createElement('tr');
          const contentsCell = document.createElement('td');
          contentsCell.textContent = item.contents;
          const writerCell = document.createElement('td');
          writerCell.textContent = item.writer;
          const writingtimeCell = document.createElement('td');
          writingtimeCell.textContent = item.writingtime;
          const placeCell = document.createElement('td');
          placeCell.textContent = item.place_id;

          row.appendChild(contentsCell);
          row.appendChild(writerCell);
          row.appendChild(writingtimeCell);
          row.appendChild(placeCell);

          tableBody.appendChild(row);
        });
      } else {
        const row = document.createElement('tr');
        const noDataCell = document.createElement('td');
        noDataCell.colSpan = 4;
        noDataCell.textContent = '댓글 쓴 내역이 없습니다.';

        row.appendChild(noDataCell);
        tableBody.appendChild(row);
      }
    })
    .catch(error => {
      console.error('Error:', error);
    });
}

// 댓글 쓴 내역 섹션 링크 클릭 시 이벤트 핸들러
const commentsLink = document.querySelector('.side-menu-nav a[href="#section3"]');
commentsLink.addEventListener('click', function(event) {
  event.preventDefault();
  getCommentListByWriter(); // 댓글 쓴 내역 가져오는 함수 호출
});

// 찜한 여행지를 AJAX로 가져오는 함수
function getFavoritePlaces() {
  fetch('/getFavoritePlaces')
    .then(response => {
      if (response.ok) {
        return response.json();
      }
      throw new Error('Error: ' + response.status);
    })
    .then(data => {
      const listContainer = document.getElementById('favoritePlacesList');
      listContainer.innerHTML = ''; // 기존의 내용을 초기화

      if (data && Array.isArray(data)) {
        data.forEach(item => {
          const listItem = document.createElement('li');
          const placeNameElement = document.createElement('span');
          placeNameElement.classList.add('place-name');
          placeNameElement.textContent = item.placeName;
          const locationElement = document.createElement('span');
          locationElement.classList.add('location');
          locationElement.textContent = item.location;

          listItem.appendChild(placeNameElement);
          listItem.appendChild(locationElement);

          listContainer.appendChild(listItem);
        });
      } else {
        const listItem = document.createElement('li');
        listItem.textContent = '찜한 여행지가 없습니다.';
        listContainer.appendChild(listItem);
      }
    })
    .catch(error => {
      console.error('Error:', error);
    });
}

// 찜한 여행지 섹션 링크 클릭 시 이벤트 핸들러
const favoritePlacesLink = document.querySelector('.side-menu-nav a[href="#section4"]');
favoritePlacesLink.addEventListener('click', function(event) {
  event.preventDefault();
  getFavoritePlaces(); // 찜한 여행지 가져오는 함수 호출
});


