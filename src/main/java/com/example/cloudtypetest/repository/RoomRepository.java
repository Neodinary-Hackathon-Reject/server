package com.example.cloudtypetest.repository;

import com.example.cloudtypetest.domain.Contest;
import com.example.cloudtypetest.domain.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByContest(Contest contest);

    @Query(value="select concat((select contest.tittle 'title' from contest where contest.id = room.contest_id limit 1), ' 외 ',\n" +
            "              count(*) - 1, '개 완료 하였습니다.')'completeProject'\n" +
            "from room_user\n" +
            "         join room on room.id = room_user.room_id\n" +
            "where room_user.user_id = :userId and room_status='FINISH'",nativeQuery = true)
    List<GetCompleteProject> getCompleteProject(@Param("userId") Long userId);
    interface GetCompleteProject {
        String getCompleteProject();
    }


    @Query(value = "select room.id'roomId' ,url'imgUrl',contest.tittle'title',concat(YEAR(start_date),'년 ',month(start_date),' 월', day(start_date),' 일 부터',YEAR(finish_date),'년 ',month(finish_date),' 월', day(finish_date),' 일 부터')'date' from contest join room on " +
            "room.contest_id=contest.id join room_user on room.id=room_user.room_id where room_user.user_id=:userId", nativeQuery = true)
    List<GetReviewProject> getReviewList(Long userId);
    interface GetReviewProject {
        Long getRoomId();
        String getImgUrl();
        String getTitle();
        String getDate();
    }
}
