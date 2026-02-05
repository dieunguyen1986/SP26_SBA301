import React, { useState } from "react";
import { Outlet } from "react-router-dom";
import { Container } from "react-bootstrap";

import "@/styles/layout.css";
import Sidebar from "@/shared/components/Sidebar";
import Topbar from "@/shared/components/Topbar";

export default function DashboardLayout() {
  const [sidebarOpen, setSidebarOpen] = useState(false);

  return (
    <div className="app-shell bg-light">
      <Sidebar open={sidebarOpen} onClose={() => setSidebarOpen(false)} />

      <div className="app-main d-flex flex-column">
        <Topbar onToggleSidebar={() => setSidebarOpen((v) => !v)} />

        <main className="flex-grow-1 py-2">
            <Outlet />
        </main>

        <footer className="py-3 text-muted small">
          <Container fluid="lg" className="text-end">
            2025, made with ♥ by SBA Training (mock)
          </Container>
        </footer>
      </div>
    </div>
  );
}
