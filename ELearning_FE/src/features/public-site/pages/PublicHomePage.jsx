import React from "react";
import HeroSection from "../components/HeroSection";
import PublicHeader from "../../../shared/components/PublicHeader";
import PopularCourseSection from "../components/PopularCourseSection";

const PublicHomePage = () => {
  return (
    <>
      <PublicHeader />

      <HeroSection />

      <PopularCourseSection />
    </>
  );
};

export default PublicHomePage;
