//package com.hackathon.mvp.infobase.controller;
//
//import com.hackathon.mvp.infobase.model.Notification;
//import com.hackathon.mvp.infobase.service.NotificationService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/notifications")
//@Slf4j
//public class NotificationController {
//
//    @Autowired
//    private NotificationService notificationService;
//
////    @GetMapping
////    public ResponseEntity<List<Notification>> getUserNotifications(@RequestParam Long userId) {
////        List<Notification> notifications = notificationService.getUserNotifications(userId);
////        return ResponseEntity.ok(notifications);
////    }
////
////    @GetMapping("/unread-count")
////    public ResponseEntity<Long> getUnreadCount(@RequestParam Long userId) {
////        long count = notificationService.getUnreadNotificationCount(userId);
////        return ResponseEntity.ok(count);
////    }
////
////    @PutMapping("/{id}/read")
////    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
////        notificationService.markAsRead(id);
////        return ResponseEntity.ok().build();
////    }
//}