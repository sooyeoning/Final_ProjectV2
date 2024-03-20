document.addEventListener('DOMContentLoaded', function() {
  const sideMenuNav = document.querySelector('.side-menu-nav');
  const sideMenuForms = document.querySelectorAll('.side-menu-form');
  const tableBody = document.getElementById('boardTableBody');
  const tableBody2 = document.getElementById('commentsTableBody');
  const tableBody3 = document.getElementById('likesTableBody');

  sideMenuNav.addEventListener('click', function(event) {
    event.preventDefault();
    if (event.target.tagName === 'A') {
      const targetSectionId = event.target.getAttribute('href');
      showSection(targetSectionId);
    }
  });

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
		console.log(data);
      // const tableBody = document.getElementById('boardTableBody');
      tableBody.innerHTML = ''; // 기존의 내용을 초기화

      if (data && Array.isArray(data)) {
        data.forEach(item => {
          const row = document.createElement('tr');
          
      const titleCell = document.createElement('td');
      const titleLink = document.createElement('a');
      titleLink.textContent = item.title;
      titleLink.href = '/detail?boardId=' + item.id; // 상세 페이지 URL로 설정
      titleCell.classList.add('board-title');
      titleCell.appendChild(titleLink);
          
          
          const boardTitleCell = document.createElement('td');
          boardTitleCell.classList.add('board-board_title');
          boardTitleCell.textContent = item.board_title;
          
          const placeCell = document.createElement('td');
          placeCell.classList.add('board-place');
          placeCell.textContent = item.place;
          
          const writerCell = document.createElement('td');
          writerCell.classList.add('board-writer');
          writerCell.textContent = item.writer;
          
          const viewsCell = document.createElement('td');
          viewsCell.classList.add('board-views');
          viewsCell.textContent = item.views;
          
          const likecountCell = document.createElement('td');
          likecountCell.classList.add('board-likecount');
          likecountCell.textContent = item.likecount;
          
          const writingtimeCell = document.createElement('td');
          writingtimeCell.classList.add('board-writingtime');
          writingtimeCell.textContent = item.writingtime;

          row.appendChild(titleCell);
          row.appendChild(boardTitleCell);
          row.appendChild(placeCell);
          row.appendChild(writerCell);
          row.appendChild(viewsCell);
          row.appendChild(likecountCell);
          row.appendChild(writingtimeCell);

          tableBody.appendChild(row);
        });
      } else {
        const row = document.createElement('tr');
        const emptyCell = document.createElement('td');
        emptyCell.setAttribute('colspan', '8');
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
  getWrittenPosts() // 글쓴 내역 가져오는 함수 호출
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
      console.log(data);
      tableBody2.innerHTML = ''; // 기존의 내용을 초기화

      if (data && Array.isArray(data)) {
        data.forEach(item => {
          const row = document.createElement('tr');
          
          // 댓글 내용(<td>)을 클릭했을 때의 이벤트 핸들러 추가
          const contentsCell = document.createElement('td');
          const contentsLink = document.createElement('a');
          contentsLink.textContent = item.contents;
          contentsLink.href = '/travelspot/post?contentId=' + item.place_id; // 댓글 상세 페이지 URL로 설정
          contentsCell.classList.add('comments-contents');
          contentsCell.appendChild(contentsLink);
          
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

          tableBody2.appendChild(row);
        });
      } else {
        const row = document.createElement('tr');
        const noDataCell = document.createElement('td');
        noDataCell.colSpan = 4;
        noDataCell.textContent = '댓글 쓴 내역이 없습니다.';

        row.appendChild(noDataCell);
        tableBody2.appendChild(row);
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

// 찜한 여행지 내역을 AJAX로 가져오는 함수
function getLikesByUserId() {
  fetch('/getLikesByUserId')
    .then(response => {
      if (response.ok) {
        return response.json();
      }
      throw new Error('Error: ' + response.status);
    })
    .then(data => {
      tableBody3.innerHTML = ''; // 기존의 내용을 초기화

      if (data && Array.isArray(data)) {
        data.forEach(item => {
          const row = document.createElement('tr');
          
          
          // 제목 (<td>)을 클릭했을 때의 이벤트 핸들러 추가
          const titleCell = document.createElement('td');
          const titleLink = document.createElement('a');
          titleLink.textContent = item.title;
          titleLink.href = '/travelspot/post?contentId=' + item.contentId; // 댓글 상세 페이지 URL로 설정
          titleCell.classList.add('place-likes');
          titleCell.appendChild(titleLink);
          
          
          const addressCell = document.createElement('td');
          addressCell.textContent = item.address;
          
          const viewcntCell = document.createElement('td');
          viewcntCell.textContent = item.viewcnt;
          
          const likecntCell = document.createElement('td');
          likecntCell.textContent = item.likecnt;

          row.appendChild(titleCell);
          row.appendChild(addressCell);
          row.appendChild(viewcntCell);
          row.appendChild(likecntCell);

          tableBody3.appendChild(row);
        });
      } else {
        const row = document.createElement('tr');
        const noDataCell = document.createElement('td');
        noDataCell.colSpan = 4;
        noDataCell.textContent = '찜한 여행지 내역이 없습니다.';

        row.appendChild(noDataCell);
        tableBody3.appendChild(row);
      }
    })
    .catch(error => {
      console.error('Error:', error);
    });
}

// 찜한 여행지 내역 섹션 링크 클릭 시 이벤트 핸들러
const likesLink = document.querySelector('.side-menu-nav a[href="#section5"]');
likesLink.addEventListener('click', function(event) {
  event.preventDefault();
  getLikesByUserId(); // 찜한 여행지 내역 가져오는 함수 호출
});
});