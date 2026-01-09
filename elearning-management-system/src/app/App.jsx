import { useState } from 'react'
import reactLogo from '../assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';
import { CourseCard } from '@/shared/components/CourseCard';

function App() {
  const [count, setCount] = useState(0)
  const courses = [
    {
      id: 1,
      title: "Design banner with Figma",
      author: "Colt stelle",
      price: 20,
      rating: 5,
      reviews: 5,
      badge: "BEST SELLER",
      image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS7qTkoE7ekNG7DN7wpIqZ9Xi8dNUefpEVKLQ&s",
      classes: 12,
      students: 120,
    },
    {
      id: 2,
      title: "We Launch Delia Webflow this Week!",
      author: "Colt stelle",
      price: 20,
      rating: 5,
      reviews: 5,
      badge: "BEST SELLER",
      image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSRwvcdF5AHeKWDcW6mU5ESnVdNY2VI3Z05Ug&s",
      classes: 12,
      students: 150,
    },
    {
      id: 3,
      title: "We Launch Delia Webflow this Week!",
      author: "Colt stelle",
      price: 20,
      rating: 4,
      reviews: 5,
      badge: "BEST SELLER",
      image: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR0bnRx3u59Q1TkwMPICpbs93MJwXwsAos78A&s",
      classes: 12,
      students: 150,
    },
    {
      id: 4,
      title: "UI/UX Design Fundamentals",
      author: "Sarah Johnson",
      price: 22,
      rating: 4.7,
      badge: "NEW",
      image: "https://picsum.photos/400/300?random=4",
      classes: 14,
      students: 85,
    },
    {
      id: 5,
      title: "JavaScript Advanced Concepts",
      author: "Jonas Schmedtmann",
      price: 35,
      rating: 4.9,
      badge: "BEST SELLER",
      image: "https://picsum.photos/400/300?random=5",
      classes: 20,
      students: 320,
    },
    {
      id: 6,
      title: "Fullstack Web Development Bootcamp",
      author: "Angela Yu",
      price: 40,
      rating: 4.8,
      badge: "TRENDING",
      image: "https://picsum.photos/400/300?random=6",
      classes: 30,
      students: 540,
    },
  ];

  return (
    <>
      <Swiper
        slidesPerView={3}
        spaceBetween={20}
        grabCursor={true}
      >
        {courses.map(course => (
          <SwiperSlide key={course.id}>
            <CourseCard course={course} />
          </SwiperSlide>
        ))}
      </Swiper>

    </>
  )
}

export default App
